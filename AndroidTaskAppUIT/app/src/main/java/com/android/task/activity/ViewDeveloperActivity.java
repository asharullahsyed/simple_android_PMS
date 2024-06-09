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
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

public class ViewDeveloperActivity extends Activity {

	ArrayList<DeveloperBean> developerBeanList;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;

	DBAdapter dbAdapter = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.__listview_main);

		listView = (ListView) findViewById(R.id.listview);
		final ArrayList<String> developerList = new ArrayList<String>();

		developerBeanList = dbAdapter.getAllDeveloper();

		for (DeveloperBean developerBean : developerBeanList) {
			String users = " FirstName: " + developerBean.getDeveloper_firstname() + "\nLastname:"
					+ developerBean.getDeveloper_lastname();

			developerList.add(users);
			Log.d("users: ", users);

		}

		listAdapter = new ArrayAdapter<String>(this, R.layout.view_developer_list, R.id.labelF, developerList);
		listView.setAdapter(listAdapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewDeveloperActivity.this);

				alertDialogBuilder.setTitle(getTitle() + "decision");
				alertDialogBuilder.setMessage("Are you sure?");

				alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						developerList.remove(position);
						listAdapter.notifyDataSetChanged();
						listAdapter.notifyDataSetInvalidated();

						dbAdapter.deleteDeveloper(developerBeanList.get(position).getDeveloper_id());
						developerBeanList = dbAdapter.getAllDeveloper();

						for (DeveloperBean developerBean : developerBeanList) {
							String users = " FirstName: " + developerBean.getDeveloper_firstname() + "\nLastname:"
									+ developerBean.getDeveloper_lastname();
							developerList.add(users);
							Log.d("users: ", users);
						}
					}

				});
				alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel", Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				// show alert
				alertDialog.show();

				return false;
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
