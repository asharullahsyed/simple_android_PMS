package com.android.task.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.task.bean.TaskBean;
import com.android.task.bean.ProjectBean;
import com.android.task.context.ApplicationContext;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

public class AddTaskActivity extends Activity {

	ArrayList<ProjectBean> projectBeanList;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	int sessionId = 0;
	String status = "P";
	Button taskSubmit;
	DBAdapter dbAdapter = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.__listview_main);

		sessionId = getIntent().getExtras().getInt("sessionId");

		listView = (ListView) findViewById(R.id.listview);
		final ArrayList<String> projectList = new ArrayList<String>();

		projectBeanList = ((ApplicationContext) AddTaskActivity.this.getApplicationContext()).getProjectBeanList();

		for (ProjectBean projectBean : projectBeanList) {
			String users = projectBean.getProject_name() + "," + projectBean.getProject_description();

			projectList.add(users);
			Log.d("users: ", users);
		}

		listAdapter = new ArrayAdapter<String>(this, R.layout.add_project_task, R.id.labelA, projectList);
		listView.setAdapter(listAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				arg0.getChildAt(arg2).setBackgroundColor(Color.TRANSPARENT);
				arg1.setBackgroundColor(334455);
				final ProjectBean projectBean = projectBeanList.get(arg2);
				final Dialog dialog = new Dialog(AddTaskActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.test_layout);
				// set title and cancelable
				RadioGroup radioGroup;
				RadioButton present;
				RadioButton absent;
				radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
				present = (RadioButton) dialog.findViewById(R.id.PresentradioButton);
				absent = (RadioButton) dialog.findViewById(R.id.AbsentradioButton);
				radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.PresentradioButton) {
							status = "P";
						} else if (checkedId == R.id.AbsentradioButton) {
							status = "A";
						}
					}
				});

				taskSubmit = (Button) dialog.findViewById(R.id.taskSubmitButton);
				taskSubmit.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						TaskBean taskBean = new TaskBean();

						taskBean.setTask_session_id(sessionId);
						taskBean.setTask_project_id(projectBean.getProject_id());
						taskBean.setTask_status(status);

						DBAdapter dbAdapter = new DBAdapter(AddTaskActivity.this);
						dbAdapter.addNewTask(taskBean);

						dialog.dismiss();
					}
				});

				dialog.setCancelable(true);
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
