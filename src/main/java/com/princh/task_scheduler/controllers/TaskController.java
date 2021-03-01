package com.princh.task_scheduler.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

import com.princh.task_scheduler.models.*;

@RestController
public class TaskController {
	@GetMapping("/")
	public TaskModel index() {
		return new TaskModel(UUID.randomUUID(), "title", "description", "priority", "status");
	}
}
