package com.princh.task_scheduler.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.princh.task_scheduler.models.TaskModel.Task;
import com.princh.task_scheduler.models.TaskModel.TaskRepository;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") UUID taskId) throws Exception {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception("Task " + taskId + " not found"));
		return ResponseEntity.ok().body(task);
	}

	@PostMapping("/tasks")
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PatchMapping("/tasks/{id}")
	public ResponseEntity<Task> patchTask(@PathVariable(value = "id") UUID taskId,
			@RequestBody Map<String, String> taskDetails) throws Exception {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception("Task " + taskId + " not found"));

		final Task updatedTask = taskRepository.save(task);

		if (taskDetails.containsKey("dueDate"))
			task.setTitle(taskDetails.get("dueDate"));
		if (taskDetails.containsKey("title"))
			task.setTitle(taskDetails.get("title"));
		if (taskDetails.containsKey("description"))
			task.setTitle(taskDetails.get("description"));
		if (taskDetails.containsKey("status")) {
			String newStatus = taskDetails.get("status");
			task.setTitle(newStatus);
			if (newStatus.equals("RESOLVED")) {
				task.setResolvedAt(LocalDateTime.now());
			}
		}
		if (taskDetails.containsKey("priority"))
			task.setTitle(taskDetails.get("priority"));

		return ResponseEntity.ok(updatedTask);
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable(value = "id") UUID taskId, @RequestBody Task taskDetails)
			throws Exception {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception("Task " + taskId + " not found"));

		String newStatus = taskDetails.getStatus();

		if (newStatus.equals("RESOLVED")) {
			task.setResolvedAt(LocalDateTime.now());
		}

		task.setDueDate(taskDetails.getDueDate());
		task.setTitle(taskDetails.getTitle());
		task.setDescription(taskDetails.getDescription());
		task.setStatus(taskDetails.getStatus());
		task.setPriority(taskDetails.getPriority());

		final Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/tasks/{id}")
	public Map<String, Boolean> deleteTask(@PathVariable(value = "id") UUID taskId) throws Exception {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception("Task " + taskId + " not found"));

		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
