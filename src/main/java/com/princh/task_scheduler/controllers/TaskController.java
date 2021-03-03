package com.princh.task_scheduler.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.princh.task_scheduler.errors.api.InvalidDateFormatException;
import com.princh.task_scheduler.errors.api.InvalidRequestBodyException;
import com.princh.task_scheduler.errors.api.ResourceNotFoundException;
import com.princh.task_scheduler.models.TaskModel.Task;
import com.princh.task_scheduler.models.TaskModel.TaskRepository;
import com.princh.task_scheduler.util.Tasks.Status;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

	@GetMapping("/tasks")
	public List<Task> getAllTasks(@RequestParam(name = "desc", required = false) String isDescending,
			@RequestParam(name = "sortBy", required = false) String sortBy) {
		List<Task> tasks = taskRepository.findAllOrderByDueDate();

		if (sortBy != null && sortBy.equals("priority")) {
			// sort by priority from HIGH to LOW
			tasks = taskRepository.findAllOrderByPriority();
		}

		if (isDescending != null && isDescending.equals("true")) {
			// reverse sort
			Collections.reverse(tasks);
		}

		return tasks;
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") UUID taskId) throws Exception {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task " + taskId + " not found"));
		return ResponseEntity.ok().body(task);
	}

	@PostMapping("/tasks")
	// Don't know how to handle these exceptions :(
	// FIXME: LocalDateTimeException on invalid dueDate
	// FIXME: PropertyValueException on null values in Task
	public Task createTask(@Valid @RequestBody Task task) throws Exception {
		Task createdTask = new Task();

		String priority = task.getPriority();
		String status = task.getStatus();
		if (!createdTask.setPriority(priority)) {
			throw new InvalidRequestBodyException(priority + " is not a valid priority");
		}
		if (!createdTask.setStatus(status)) {
			throw new InvalidRequestBodyException(status + " is not a valid status");
		}

		if (task.getStatus().equals(Status.RESOLVED.label)) {
			task.setResolvedAt(LocalDateTime.now());
		}

		createdTask = taskRepository.save(task);

		return createdTask;
	}

	@PatchMapping("/tasks/{id}")
	// Not sure if this is the proper way to handle a patch request in Spring
	public ResponseEntity<Task> patchTask(@PathVariable(value = "id") UUID taskId,
			@RequestBody Map<String, String> taskDetails) throws Exception {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task " + taskId + " not found"));

		if (taskDetails.containsKey("dueDate")) {
			try {
				task.setDueDate(LocalDateTime.parse(taskDetails.get("dueDate"), formatter));
			} catch (DateTimeParseException e) {
				throw new InvalidDateFormatException("Date format must be MM-dd-yyyy HH:mm:ss");
			}
		}

		if (taskDetails.containsKey("title"))
			task.setTitle(taskDetails.get("title"));

		if (taskDetails.containsKey("description"))
			task.setDescription(taskDetails.get("description"));

		if (taskDetails.containsKey("status")) {
			// sets resolved date if status updated to RESOLVE
			String newStatus = taskDetails.get("status");
			if (!task.setStatus(newStatus)) {
				throw new InvalidRequestBodyException(taskDetails.get("status") + " is not a valid status");
			}
			if (newStatus.equals(Status.RESOLVED.label)) {
				task.setResolvedAt(LocalDateTime.now());
			}
		}

		if (taskDetails.containsKey("priority")) {
			if (!task.setPriority(taskDetails.get("priority"))) {
				throw new InvalidRequestBodyException(taskDetails.get("priority") + " is not a valid priority");
			}
		}

		final Task updatedTask = taskRepository.save(task);

		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/tasks/{id}")
	public Map<String, Boolean> deleteTask(@PathVariable(value = "id") UUID taskId) throws Exception {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task " + taskId + " not found"));

		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
