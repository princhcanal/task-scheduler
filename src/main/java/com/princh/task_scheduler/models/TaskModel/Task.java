package com.princh.task_scheduler.models.TaskModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Tasks")
public class Task {

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

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
