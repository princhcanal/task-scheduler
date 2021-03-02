package com.princh.task_scheduler.models.TaskModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.princh.task_scheduler.errors.api.InvalidRequestBodyException;
import com.princh.task_scheduler.util.Tasks.Priority;
import com.princh.task_scheduler.util.Tasks.Status;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Tasks")
public class Task implements ITask {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@CreationTimestamp
	@Column(name = "createdAt")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updatedAt")
	private Date updatedAt;

	@Column(name = "dueDate")
	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime dueDate;

	@Column(name = "resolvedAt")
	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime resolvedAt;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "priority", nullable = false)
	private String priority;

	public Task() {
	}

	public UUID getId() {
		return this.id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public LocalDateTime getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getResolvedAt() {
		return this.resolvedAt;
	}

	public void setResolvedAt(LocalDateTime resolvedAt) {
		this.resolvedAt = resolvedAt;
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

	public String getStatus() {
		return this.status;
	}

	public boolean setStatus(String status) {
		for (Status s : Status.values()) {
			if (s.name().equals(status)) {
				this.status = status;
				return true;
			}
		}
		return false;
	}

	public boolean setPriority(String priority) {
		for (Priority p : Priority.values()) {
			if (p.name().equals(priority)) {
				this.priority = priority;
				return true;
			}
		}
		return false;
	}

}
