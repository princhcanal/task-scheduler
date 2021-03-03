package com.princh.task_scheduler.schedulers;

import java.time.LocalDateTime;

import com.princh.task_scheduler.errors.api.InvalidDateFormatException;
import com.princh.task_scheduler.models.TaskModel.Task;
import com.princh.task_scheduler.models.TaskModel.TaskRepository;
import com.princh.task_scheduler.util.Tasks.Status;
import com.princh.task_scheduler.util.Tasks.Priority;
import com.princh.task_scheduler.util.Tasks.RandomTaskGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TasksScheduler {
	@Autowired
	private TaskRepository taskRepository;

	private final int intervalLength = 15000;
	private int numTasksPerInterval = 5;

	private static final Logger log = LoggerFactory.getLogger(TasksScheduler.class);

	@Scheduled(fixedRate = intervalLength)
	public void createMultipleTasks() {
		for (int i = 0; i < numTasksPerInterval; i++) {
			createTask();
		}
	}

	public void createTask() {
		Task task = RandomTaskGenerator.generateRandomTask();
		taskRepository.save(task);

		log.info("=====New Task Created=====");
		log.info("Due date: " + task.getDueDate().toString());
		log.info("Title: " + task.getTitle());
		log.info("Description: " + task.getDescription());
		log.info("Status: " + task.getStatus());
		log.info("Priority: " + task.getPriority());
	}

	public void setNumTasksPerInterval(int numTasksPerInterval) {
		this.numTasksPerInterval = numTasksPerInterval;
	}
}