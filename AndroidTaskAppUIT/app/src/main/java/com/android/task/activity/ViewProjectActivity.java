package com.android.task.activity;

import com.example.androidtasksystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ViewProjectActivity extends Activity {

	Spinner spinnerbranch, spinneryear;
	String userrole, branch, year;
	private String[] branchString = new String[]{"IT", "HR", "Finance"};
	private String[] yearString = new String[]{"Development", "QA", "Support"};

	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewproject);

		spinnerbranch = (Spinner) findViewById(R.id.spinnerbranchView);
		spinneryear = (Spinner) findViewById(R.id.spinneryearView);

		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
									   int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch = (String) spinnerbranch.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, branchString);
		adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);

		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
									   int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				year = (String) spinneryear.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, yearString);
		adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinneryear.setAdapter(adapter_year);

		submit = (Button) findViewById(R.id.submitButton);
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ViewProjectActivity.this, ViewProjectByBranchYear.class);
				intent.putExtra("branch", branch);
				intent.putExtra("year", year);
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


//package com.android.task.activity;
//
//import com.example.androidtasksystem.R;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.AdapterView.OnItemSelectedListener;
//
//public class ViewProjectActivity extends Activity {
//
//	Spinner spinnerbranch,spinneryear;
//	String userrole,branch,year;
//	private String[] branchString = new String[] { "cse"};
//	private String[] yearString = new String[] {"SE","TE","BE"};
//
//	Button submit;
//
//
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.viewproject);
//
//		spinnerbranch=(Spinner)findViewById(R.id.spinnerbranchView);
//		spinneryear=(Spinner)findViewById(R.id.spinneryearView);
//
//
//		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view,
//									   int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				branch =(String) spinnerbranch.getSelectedItem();
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
//
//		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item, branchString);
//		adapter_branch
//				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerbranch.setAdapter(adapter_branch);
//
//		///......................spinner2
//
//		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view,
//									   int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				year =(String) spinneryear.getSelectedItem();
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
//
//		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item, yearString);
//		adapter_year
//				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinneryear.setAdapter(adapter_year);
//
//		submit=(Button)findViewById(R.id.submitButton);
//		submit.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//
//				Intent intent = new Intent(ViewProjectActivity.this,ViewProjectByBranchYear.class);
//				intent.putExtra("branch", branch);
//				intent.putExtra("year", year);
//				startActivity(intent);
//
//			}
//		});
//
//	}
//
//
//
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//}
