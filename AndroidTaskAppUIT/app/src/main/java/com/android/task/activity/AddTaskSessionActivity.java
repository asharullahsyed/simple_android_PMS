package com.android.task.activity;

import java.util.ArrayList;
import java.util.Calendar;

import com.android.task.bean.TaskBean;
import com.android.task.bean.TaskSessionBean;
import com.android.task.bean.DeveloperBean;
import com.android.task.bean.ProjectBean;
import com.android.task.context.ApplicationContext;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskSessionActivity extends Activity {

	private ImageButton date;
	private Calendar cal;
	private int day;
	private int month;
	private int dyear;
	private EditText dateEditText;
	Button submit;
	Button viewTask;
	Button viewTotalTask;
	Spinner spinnerbranch, spinneryear, spinnerSubject;
	String branch = "IT";
	String year = "Development";
	String subject = "Alpha";

	private String[] branchString = new String[]{"IT", "HR", "Finance"};
	private String[] yearString = new String[]{"Development", "QA", "Support"};
	private String[] subjectSEString = new String[]{"Alpha", "Beta"};
	private String[] subjectTEString = new String[]{"Gamma", "Delta"};
	private String[] subjectBEString = new String[]{"Epsilon", "Zeta"};

	private String[] subjectFinal = new String[]{"Project1", "Project2", "Project3", "Project4", "Project5", "Project6"};
	TaskSessionBean taskSessionBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task);

		spinnerbranch = findViewById(R.id.spinner1);
		spinneryear = findViewById(R.id.spinneryear);
		spinnerSubject = findViewById(R.id.spinnerSE);

		ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchString);
		adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);
		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch = (String) spinnerbranch.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});

		ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearString);
		adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinneryear.setAdapter(adapter_year);
		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				year = (String) spinneryear.getSelectedItem();
				Toast.makeText(getApplicationContext(), "year:" + year, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});

		ArrayAdapter<String> adapter_subject = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectFinal);
		adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSubject.setAdapter(adapter_subject);
		spinnerSubject.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				subject = (String) spinnerSubject.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});

		date = findViewById(R.id.DateImageButton);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		dyear = cal.get(Calendar.YEAR);
		dateEditText = findViewById(R.id.DateEditText);
		date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showDialog(0);
			}
		});

		submit = findViewById(R.id.buttonsubmit);
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				taskSessionBean = new TaskSessionBean();
				DeveloperBean bean = ((ApplicationContext) getApplicationContext()).getDeveloperBean();

				taskSessionBean.setTask_session_developer_id(bean.getDeveloper_id());
				taskSessionBean.setTask_session_department(branch);
				taskSessionBean.setTask_session_class(year);
				taskSessionBean.setTask_session_date(dateEditText.getText().toString());
				taskSessionBean.setTask_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddTaskSessionActivity.this);
				int sessionId = dbAdapter.addTaskSession(taskSessionBean);

				ArrayList<ProjectBean> projectBeanList = dbAdapter.getAllProjectByBranchYear(branch, year);
				((ApplicationContext) getApplicationContext()).setProjectBeanList(projectBeanList);

				Intent intent = new Intent(AddTaskSessionActivity.this, AddTaskActivity.class);
				intent.putExtra("sessionId", sessionId);
				startActivity(intent);
			}
		});

		viewTask = findViewById(R.id.viewTaskbutton);
		viewTask.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				taskSessionBean = new TaskSessionBean();
				DeveloperBean bean = ((ApplicationContext) getApplicationContext()).getDeveloperBean();

				taskSessionBean.setTask_session_developer_id(bean.getDeveloper_id());
				taskSessionBean.setTask_session_department(branch);
				taskSessionBean.setTask_session_class(year);
				taskSessionBean.setTask_session_date(dateEditText.getText().toString());
				taskSessionBean.setTask_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddTaskSessionActivity.this);
				ArrayList<TaskBean> taskBeanList = dbAdapter.getTaskBySessionID(taskSessionBean);
				((ApplicationContext) getApplicationContext()).setTaskBeanList(taskBeanList);

				Intent intent = new Intent(AddTaskSessionActivity.this, ViewTaskByDeveloperActivity.class);
				startActivity(intent);
			}
		});

		viewTotalTask = findViewById(R.id.viewTotalTaskButton);
		viewTotalTask.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				taskSessionBean = new TaskSessionBean();
				DeveloperBean bean = ((ApplicationContext) getApplicationContext()).getDeveloperBean();

				taskSessionBean.setTask_session_developer_id(bean.getDeveloper_id());
				taskSessionBean.setTask_session_department(branch);
				taskSessionBean.setTask_session_class(year);
				taskSessionBean.setTask_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddTaskSessionActivity.this);
				ArrayList<TaskBean> taskBeanList = dbAdapter.getTotalTaskBySessionID(taskSessionBean);
				((ApplicationContext) getApplicationContext()).setTaskBeanList(taskBeanList);

				Intent intent = new Intent(AddTaskSessionActivity.this, ViewTaskByDeveloperActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, dyear, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
		}
	};
}


























//public class AddTaskSessionActivity extends Activity {
//
//	private ImageButton date;
//	private Calendar cal;
//	private int day;
//	private int month;
//	private int dyear;
//	private EditText dateEditText;
//	Button submit;
//	Button viewTask;
//	Button viewTotalTask;
//	Spinner spinnerbranch, spinneryear, spinnerSubject;
//	String branch = "cse";
//	String year = "SE";
//	String subject = "SC";
//
//	private String[] branchString = new String[]{"cse"};
//	private String[] yearString = new String[]{"SE", "TE", "BE"};
//	private String[] subjectSEString = new String[]{"SC", "MC"};
//	private String[] subjectTEString = new String[]{"GT", "CN"};
//	private String[] subjectBEString = new String[]{"DS", "NS"};
//
//	private String[] subjectFinal = new String[]{"M3", "DS", "M4", "CN", "M5", "NS"};
//	TaskSessionBean taskSessionBean;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.add_task);
//
//		spinnerbranch = findViewById(R.id.spinner1);
//		spinneryear = findViewById(R.id.spinneryear);
//		spinnerSubject = findViewById(R.id.spinnerSE);
//
//		ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchString);
//		adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerbranch.setAdapter(adapter_branch);
//		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				branch = (String) spinnerbranch.getSelectedItem();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {}
//		});
//
//		ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearString);
//		adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinneryear.setAdapter(adapter_year);
//		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				year = (String) spinneryear.getSelectedItem();
//				Toast.makeText(getApplicationContext(), "year:" + year, Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {}
//		});
//
//		ArrayAdapter<String> adapter_subject = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectFinal);
//		adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerSubject.setAdapter(adapter_subject);
//		spinnerSubject.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
//				subject = (String) spinnerSubject.getSelectedItem();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {}
//		});
//
//		date = findViewById(R.id.DateImageButton);
//		cal = Calendar.getInstance();
//		day = cal.get(Calendar.DAY_OF_MONTH);
//		month = cal.get(Calendar.MONTH);
//		dyear = cal.get(Calendar.YEAR);
//		dateEditText = findViewById(R.id.DateEditText);
//		date.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				showDialog(0);
//			}
//		});
//
//		submit = findViewById(R.id.buttonsubmit);
//		submit.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				taskSessionBean = new TaskSessionBean();
//				DeveloperBean bean = ((ApplicationContext) getApplicationContext()).getDeveloperBean();
//
//				taskSessionBean.setTask_session_developer_id(bean.getDeveloper_id());
//				taskSessionBean.setTask_session_department(branch);
//				taskSessionBean.setTask_session_class(year);
//				taskSessionBean.setTask_session_date(dateEditText.getText().toString());
//				taskSessionBean.setTask_session_subject(subject);
//
//				DBAdapter dbAdapter = new DBAdapter(AddTaskSessionActivity.this);
//				int sessionId = dbAdapter.addTaskSession(taskSessionBean);
//
//				ArrayList<ProjectBean> projectBeanList = dbAdapter.getAllProjectByBranchYear(branch, year);
//				((ApplicationContext) getApplicationContext()).setProjectBeanList(projectBeanList);
//
//				Intent intent = new Intent(AddTaskSessionActivity.this, AddTaskActivity.class);
//				intent.putExtra("sessionId", sessionId);
//				startActivity(intent);
//			}
//		});
//
//		viewTask = findViewById(R.id.viewTaskbutton);
//		viewTask.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				taskSessionBean = new TaskSessionBean();
//				DeveloperBean bean = ((ApplicationContext) getApplicationContext()).getDeveloperBean();
//
//				taskSessionBean.setTask_session_developer_id(bean.getDeveloper_id());
//				taskSessionBean.setTask_session_department(branch);
//				taskSessionBean.setTask_session_class(year);
//				taskSessionBean.setTask_session_date(dateEditText.getText().toString());
//				taskSessionBean.setTask_session_subject(subject);
//
//				DBAdapter dbAdapter = new DBAdapter(AddTaskSessionActivity.this);
//				ArrayList<TaskBean> taskBeanList = dbAdapter.getTaskBySessionID(taskSessionBean);
//				((ApplicationContext) getApplicationContext()).setTaskBeanList(taskBeanList);
//
//				Intent intent = new Intent(AddTaskSessionActivity.this, ViewTaskByDeveloperActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		viewTotalTask = findViewById(R.id.viewTotalTaskButton);
//		viewTotalTask.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				taskSessionBean = new TaskSessionBean();
//				DeveloperBean bean = ((ApplicationContext) getApplicationContext()).getDeveloperBean();
//
//				taskSessionBean.setTask_session_developer_id(bean.getDeveloper_id());
//				taskSessionBean.setTask_session_department(branch);
//				taskSessionBean.setTask_session_class(year);
//				taskSessionBean.setTask_session_subject(subject);
//
//				DBAdapter dbAdapter = new DBAdapter(AddTaskSessionActivity.this);
//				ArrayList<TaskBean> taskBeanList = dbAdapter.getTotalTaskBySessionID(taskSessionBean);
//				((ApplicationContext) getApplicationContext()).setTaskBeanList(taskBeanList);
//
//				Intent intent = new Intent(AddTaskSessionActivity.this, ViewTaskByDeveloperActivity.class);
//				startActivity(intent);
//			}
//		});
//	}
//
////	@Override
////	@Deprecated
////	protected Dialog onCreateDialog(int id) {
////		return new DatePickerDialog(this, datePickerListener, dyear, month, day);
////	}
////
////	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
////		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
////			dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
////		}
////	};
////}
//
//
//
//import java.util.ArrayList;
//		import java.util.Calendar;
//
//		import com.android.task.bean.TaskBean;
//		import com.android.task.bean.TaskSessionBean;
//		import com.android.task.bean.DeveloperBean;
//		import com.android.task.bean.ProjectBean;
//		import com.android.task.context.ApplicationContext;
//		import com.android.task.db.DBAdapter;
//		import com.example.androidtasksystem.R;
//
//		import android.app.Activity;
//		import android.app.DatePickerDialog;
//		import android.app.Dialog;
//		import android.content.Intent;
//		import android.graphics.Color;
//		import android.os.Bundle;
//		import android.view.Menu;
//		import android.view.View;
//		import android.view.View.OnClickListener;
//		import android.widget.AdapterView;
//		import android.widget.AdapterView.OnItemSelectedListener;
//		import android.widget.ArrayAdapter;
//		import android.widget.Button;
//		import android.widget.DatePicker;
//		import android.widget.EditText;
//		import android.widget.ImageButton;
//		import android.widget.Spinner;
//		import android.widget.TextView;
//		import android.widget.Toast;
