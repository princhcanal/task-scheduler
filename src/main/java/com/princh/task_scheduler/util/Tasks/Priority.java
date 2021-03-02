package com.princh.task_scheduler.util.Tasks;

public enum Priority {
	LOW("LOW"), MEDIUM("MEDIUM"), HIGH("HIGH");

	public final String label;

	private Priority(String label) {
		this.label = label;
	}
}
