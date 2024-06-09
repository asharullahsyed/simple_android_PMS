package com.android.task.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.task.bean.TaskBean;
import com.android.task.bean.DeveloperBean;
import com.android.task.bean.ProjectBean;
import com.android.task.context.ApplicationContext;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

public class ViewTaskByDeveloperActivity extends Activity {

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
		taskList.add("Id | ProjectName | Status");

		taskBeanList = ((ApplicationContext) ViewTaskByDeveloperActivity.this.getApplicationContext()).getTaskBeanList();

		for (TaskBean taskBean : taskBeanList) {
			String users = "";
			if (taskBean.getTask_session_id() != 0) {
				DBAdapter dbAdapter = new DBAdapter(ViewTaskByDeveloperActivity.this);
				ProjectBean projectBean = dbAdapter.getProjectById(taskBean.getTask_project_id());
				users = taskBean.getTask_project_id() + ". " + projectBean.getProject_name() + "," + projectBean.getProject_description() + " " + taskBean.getTask_status();
			} else {
				users = taskBean.getTask_status();
			}

			taskList.add(users);
			Log.d("users: ", users);
		}

		listAdapter = new ArrayAdapter<String>(this, R.layout.view_task_list, R.id.labelTask, taskList);
		listView.setAdapter(listAdapter);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
