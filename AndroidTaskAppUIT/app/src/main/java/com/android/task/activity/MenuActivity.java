package com.android.task.activity;

import java.util.ArrayList;

import com.android.task.bean.TaskBean;
import com.android.task.context.ApplicationContext;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	Button addProject;
	Button addDeveloper;
	Button viewProject;
	Button viewDeveloper;
	Button logout;
	Button taskPerProject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		addProject = (Button) findViewById(R.id.buttonaddproject);
		addDeveloper = (Button) findViewById(R.id.buttonadddeveloper);
		viewProject = (Button) findViewById(R.id.buttonViewproject);
		viewDeveloper = (Button) findViewById(R.id.buttonviewdeveloper);
		logout = (Button) findViewById(R.id.buttonlogout);

		addProject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, AddProjectActivity.class);
				startActivity(intent);
			}
		});

		addDeveloper.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, AddDeveloperActivity.class);
				startActivity(intent);
			}
		});

		viewDeveloper.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, ViewDeveloperActivity.class);
				startActivity(intent);
			}
		});

		viewProject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, ViewProjectActivity.class);
				startActivity(intent);
			}
		});

		logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		taskPerProject = (Button) findViewById(R.id.taskPerProjectButton);
		taskPerProject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
				ArrayList<TaskBean> taskBeanList = dbAdapter.getAllTaskByProject();
				((ApplicationContext)MenuActivity.this.getApplicationContext()).setTaskBeanList(taskBeanList);

				Intent intent = new Intent(MenuActivity.this, ViewTaskPerProjectActivity.class);
				startActivity(intent);

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
