package com.android.task.activity;

import com.android.task.bean.ProjectBean;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddProjectActivity extends Activity {

	Button registerButton;
	EditText textFirstName;
	EditText textLastName;
	EditText textContact;
	EditText textAddress;
	Spinner spinnerBranch, spinnerYear;
	String branch, year;
	private String[] branchString = new String[]{"IT", "HR", "Finance"};
	private String[] yearString = new String[]{"Development", "QA", "Support"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproject);

		spinnerBranch = (Spinner) findViewById(R.id.spinnerdept);
		spinnerYear = (Spinner) findViewById(R.id.spinneryear);
		textFirstName = (EditText) findViewById(R.id.editTextFirstName);
		textLastName = (EditText) findViewById(R.id.editTextLastName);
		textContact = (EditText) findViewById(R.id.editTextPhone);
		textAddress = (EditText) findViewById(R.id.editTextaddr);
		registerButton = (Button) findViewById(R.id.RegisterButton);

		spinnerBranch.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch = (String) spinnerBranch.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		ArrayAdapter<String> adapterBranch = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branchString);
		adapterBranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerBranch.setAdapter(adapterBranch);

		spinnerYear.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				year = (String) spinnerYear.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearString);
		adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerYear.setAdapter(adapterYear);

		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String firstName = textFirstName.getText().toString();
				String lastName = textLastName.getText().toString();
				String phoneNo = textContact.getText().toString();
				String address = textAddress.getText().toString();

				if (TextUtils.isEmpty(firstName)) {
					textFirstName.setError("Please enter project name");
				} else if (TextUtils.isEmpty(lastName)) {
					textLastName.setError("Please enter project description");
				} else if (TextUtils.isEmpty(phoneNo)) {
					textContact.setError("Please enter contact number");
				} else if (TextUtils.isEmpty(address)) {
					textAddress.setError("Please enter address");
				} else {
					ProjectBean projectBean = new ProjectBean();
					projectBean.setProject_name(firstName);
					projectBean.setProject_description(lastName);
					projectBean.setProject_mobilenumber(phoneNo);
					projectBean.setProject_address(address);
					projectBean.setProject_department(branch);
					projectBean.setProject_class(year);

					DBAdapter dbAdapter = new DBAdapter(AddProjectActivity.this);
					dbAdapter.addProject(projectBean);

					Intent intent = new Intent(AddProjectActivity.this, MenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Project added successfully", Toast.LENGTH_SHORT).show();
				}
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
//import com.android.task.bean.ProjectBean;
//import com.android.task.db.DBAdapter;
//import com.example.androidtasksystem.R;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class AddProjectActivity extends Activity {
//
//	Button registerButton;
//	EditText textFirstName;
//	EditText textLastName;
//	EditText textContact;
//	EditText textAddress;
//	Spinner spinnerBranch, spinnerYear;
//	String branch, year;
//	private String[] branchString = new String[]{"cse"};
//	private String[] yearString = new String[]{"SE", "TE", "BE"};
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.addproject);
//
//		spinnerBranch = (Spinner) findViewById(R.id.spinnerdept);
//		spinnerYear = (Spinner) findViewById(R.id.spinneryear);
//		textFirstName = (EditText) findViewById(R.id.editTextFirstName);
//		textLastName = (EditText) findViewById(R.id.editTextLastName);
//		textContact = (EditText) findViewById(R.id.editTextPhone);
//		textAddress = (EditText) findViewById(R.id.editTextaddr);
//		registerButton = (Button) findViewById(R.id.RegisterButton);
//
//		spinnerBranch.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				branch = (String) spinnerBranch.getSelectedItem();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}
//		});
//
//		ArrayAdapter<String> adapterBranch = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branchString);
//		adapterBranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerBranch.setAdapter(adapterBranch);
//
//		spinnerYear.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				year = (String) spinnerYear.getSelectedItem();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}
//		});
//
//		ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearString);
//		adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerYear.setAdapter(adapterYear);
//
//		registerButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				String firstName = textFirstName.getText().toString();
//				String lastName = textLastName.getText().toString();
//				String phoneNo = textContact.getText().toString();
//				String address = textAddress.getText().toString();
//
//				if (TextUtils.isEmpty(firstName)) {
//					textFirstName.setError("Please enter first name");
//				} else if (TextUtils.isEmpty(lastName)) {
//					textLastName.setError("Please enter last name");
//				} else if (TextUtils.isEmpty(phoneNo)) {
//					textContact.setError("Please enter phone number");
//				} else if (TextUtils.isEmpty(address)) {
//					textAddress.setError("Please enter address");
//				} else {
//					ProjectBean projectBean = new ProjectBean();
//					projectBean.setProject_name(firstName);
//					projectBean.setProject_description(lastName);
//					projectBean.setProject_mobilenumber(phoneNo);
//					projectBean.setProject_address(address);
//					projectBean.setProject_department(branch);
//					projectBean.setProject_class(year);
//
//					DBAdapter dbAdapter = new DBAdapter(AddProjectActivity.this);
//					dbAdapter.addProject(projectBean);
//
//					Intent intent = new Intent(AddProjectActivity.this, MenuActivity.class);
//					startActivity(intent);
//					Toast.makeText(getApplicationContext(), "Project added successfully", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//}
