package com.princh.task_scheduler.models.TaskModel;

import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "priority", nullable = false)
	private String priority;

	@Column(name = "status", nullable = false)
	private String status;

	public Task() {
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
