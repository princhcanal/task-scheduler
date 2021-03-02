package com.princh.task_scheduler.util.Tasks;

public enum Status {
	QUEUED("QUEUED"), RESOLVED("RESOLVED");

	public final String label;

	private Status(String label) {
		this.label = label;
	}
}
