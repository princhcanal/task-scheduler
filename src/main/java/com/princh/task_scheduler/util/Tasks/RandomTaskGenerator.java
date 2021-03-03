package com.princh.task_scheduler.util.Tasks;

import java.util.Random;

import java.time.LocalDateTime;

import com.princh.task_scheduler.models.TaskModel.Task;

public class RandomTaskGenerator {
	private final static String[] titles = { "Do homework", "Do project", "Study", "Clean room", "Wash dishes",
			"Hire Princh :)" };
	private final static String[] priorities = { "LOW", "MEDIUM", "HIGH" };

	public static Task generateRandomTask() {
		Task task = new Task();
		try {
			task.setDueDate(getRandomDueDate());
		} catch (Exception e) {
		}
		task.setTitle(getRandomTitle());
		task.setDescription("The task is to " + task.getTitle());
		task.setStatus("QUEUED");
		task.setPriority(getRandomPriority());
		return task;
	}

	private static LocalDateTime getRandomDueDate() {
		return LocalDateTime.now().plusDays(new Random().nextInt(50));
	}

	private static String getRandomTitle() {
		return titles[new Random().nextInt(titles.length)];
	}

	private static String getRandomPriority() {
		return priorities[new Random().nextInt(priorities.length)];
	}
}
