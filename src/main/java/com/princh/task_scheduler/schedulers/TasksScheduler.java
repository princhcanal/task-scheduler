package com.princh.task_scheduler.schedulers;

import java.time.LocalDateTime;

import com.princh.task_scheduler.models.TaskModel.Task;
import com.princh.task_scheduler.models.TaskModel.TaskRepository;

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
	public static int count = 0;

	private static final Logger log = LoggerFactory.getLogger(TasksScheduler.class);

	@Scheduled(fixedRate = intervalLength)
	public void createMultipleTasks() {
		for (int i = 0; i < numTasksPerInterval; i++) {
			// createTask();
		}
	}

	public void createTask() {
		Task task = new Task();

		task.setDueDate(LocalDateTime.now().plusDays(7));
		task.setTitle("Task " + count);
		task.setDescription("This is task " + count);
		task.setStatus("Unfinished");
		task.setPriority("Low");
		taskRepository.save(task);

		log.info("Task " + task.getTitle() + " created");
		count++;
	}

	public void setNumTasksPerInterval(int numTasksPerInterval) {
		this.numTasksPerInterval = numTasksPerInterval;
	}
}