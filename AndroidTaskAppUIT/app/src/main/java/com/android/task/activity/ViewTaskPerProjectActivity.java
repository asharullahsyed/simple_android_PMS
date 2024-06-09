package com.android.task.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.task.bean.ProjectBean;
import com.android.task.bean.TaskBean;
import com.android.task.bean.DeveloperBean;
import com.android.task.context.ApplicationContext;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

public class ViewTaskPerProjectActivity extends Activity {

	ArrayList<TaskBean> taskBeanList;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;

	DBAdapter dbAdapter = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.__listview_main);

		listView = (ListView) findViewById(R.id.listview);
		final ArrayList<String> taskList = new ArrayList<String>();
		taskList.add("Task Count Per Project");

		taskBeanList = ((ApplicationContext) ViewTaskPerProjectActivity.this.getApplicationContext())
				.getTaskBeanList();

		for(TaskBean taskBean : taskBeanList)
		{
			String users = "";

			DBAdapter dbAdapter = new DBAdapter(ViewTaskPerProjectActivity.this);
			ProjectBean projectBean = dbAdapter.getProjectById(taskBean.getTask_project_id());
			users = taskBean.getTask_project_id()+".     "+projectBean.getProject_name()+","+projectBean.getProject_description()+"                  "+taskBean.getTask_session_id();
			taskList.add(users);
		}


		listAdapter = new ArrayAdapter<String>(this, R.layout.view_task_list_per_project,
				R.id.labelTaskPerProject, taskList);
		listView.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
