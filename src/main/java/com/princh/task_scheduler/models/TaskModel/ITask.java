package com.princh.task_scheduler.models.TaskModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.princh.task_scheduler.util.Tasks.Priority;
import com.princh.task_scheduler.util.Tasks.Status;

public interface ITask {
	UUID getId();

	Date getCreatedAt();

	Date getUpdatedAt();

	LocalDateTime getDueDate();

	boolean setDueDate(LocalDateTime dueDate) throws Exception;

	LocalDateTime getResolvedAt();

	void setResolvedAt(LocalDateTime resolvedAt);

	String getTitle();

	void setTitle(String title);

	String getDescription();

	void setDescription(String description);

	String getStatus();

	boolean setStatus(String status);

	String getPriority();

	boolean setPriority(String priority);
}
