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

import com.android.task.bean.DeveloperBean;
import com.android.task.bean.ProjectBean;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

public class ViewProjectByBranchYear extends Activity {

	ArrayList<ProjectBean> projectBeanList;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	String branch;
	String year;

	DBAdapter dbAdapter = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);

		listView = findViewById(R.id.listview);
		final ArrayList<String> projectList = new ArrayList<>();

		branch = getIntent().getExtras().getString("branch");
		year = getIntent().getExtras().getString("year");

		projectBeanList = dbAdapter.getAllProjectByBranchYear(branch, year);

		for (ProjectBean projectBean : projectBeanList) {
			String users = projectBean.getProject_name() + "," + projectBean.getProject_description();
			projectList.add(users);
			Log.d("users: ", users);
		}

		listAdapter = new ArrayAdapter<>(this, R.layout.view_project_list, R.id.label, projectList);
		listView.setAdapter(listAdapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewProjectByBranchYear.this);

				alertDialogBuilder.setTitle(getTitle() + " decision");
				alertDialogBuilder.setMessage("Are you sure?");

				alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dbAdapter.deleteProject(projectBeanList.get(position).getProject_id());
						projectBeanList.remove(position);
						projectList.remove(position);
						listAdapter.notifyDataSetChanged();
					}
				});

				alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You chose cancel", Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();

				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
