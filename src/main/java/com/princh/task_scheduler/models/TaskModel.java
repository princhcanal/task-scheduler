package com.princh.task_scheduler.models;

import java.util.UUID;

public class TaskModel {
	private final UUID id;
	private final String title;
	private final String description;
	private final String priority;
	private final String status;

	public TaskModel(UUID id, String title, String description, String priority, String status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
	}

	public UUID getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public String getPriority() {
		return this.priority;
	}

	public String getStatus() {
		return this.status;
	}

}
