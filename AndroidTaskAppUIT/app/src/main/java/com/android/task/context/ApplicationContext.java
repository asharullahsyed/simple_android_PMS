package com.android.task.context;

import java.util.ArrayList;

import android.app.Application;

import com.android.task.bean.DeveloperBean;
import com.android.task.bean.TaskBean;
import com.android.task.bean.TaskSessionBean;
import com.android.task.bean.ProjectBean;

public class ApplicationContext extends Application {
	private DeveloperBean developerBean;
	private TaskSessionBean taskSessionBean;
	private ArrayList<ProjectBean> projectBeanList;
	private ArrayList<TaskBean> taskBeanList;

	public DeveloperBean getDeveloperBean() {
		return developerBean;
	}
	public void setDeveloperBean(DeveloperBean developerBean) {
		this.developerBean = developerBean;
	}
	public TaskSessionBean getTaskSessionBean() {
		return taskSessionBean;
	}
	public void setTaskSessionBean(TaskSessionBean taskSessionBean) {
		this.taskSessionBean = taskSessionBean;
	}
	public ArrayList<ProjectBean> getProjectBeanList() {
		return projectBeanList;
	}
	public void setProjectBeanList(ArrayList<ProjectBean> projectBeanList) {
		this.projectBeanList = projectBeanList;
	}
	public ArrayList<TaskBean> getTaskBeanList() {
		return taskBeanList;
	}
	public void setTaskBeanList(ArrayList<TaskBean> taskBeanList) {
		this.taskBeanList = taskBeanList;
	}
}
