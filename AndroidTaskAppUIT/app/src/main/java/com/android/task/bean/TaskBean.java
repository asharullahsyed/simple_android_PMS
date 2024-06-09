package com.android.task.bean;

public class TaskBean {

	private int task_session_id;
	private int task_project_id;
	private String task_status;

	public int getTask_session_id() {
		return task_session_id;
	}
	public void setTask_session_id(int task_session_id) {
		this.task_session_id = task_session_id;
	}
	public int getTask_project_id() {
		return task_project_id;
	}
	public void setTask_project_id(int task_project_id) {
		this.task_project_id = task_project_id;
	}
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
}
