package com.princh.task_scheduler.models.TaskModel;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, UUID> {
	@Query(value = "SELECT * FROM tasks ORDER BY due_date", nativeQuery = true)
	public List<Task> findAllOrderByDueDate();

	@Query(value = "SELECT * FROM tasks WHERE priority IN ('LOW', 'MEDIUM', 'HIGH') ORDER BY CASE priority WHEN 'HIGH' THEN 1 WHEN 'MEDIUM' THEN 2 WHEN 'LOW' THEN 3 END, due_date", nativeQuery = true)
	public List<Task> findAllOrderByPriority();
}
