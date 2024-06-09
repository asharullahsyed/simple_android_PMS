package com.android.task.activity;

import java.util.ArrayList;

import com.android.task.bean.TaskBean;
import com.android.task.bean.TaskSessionBean;
import com.android.task.bean.DeveloperBean;
import com.android.task.bean.ProjectBean;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestActivity extends Activity {

	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);

		submit = (Button)findViewById(R.id.button1);

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DBAdapter dbAdapter = new DBAdapter(TestActivity.this);
				TaskSessionBean taskSessionBean = new TaskSessionBean();


				taskSessionBean.setTask_session_developer_id(1);
				taskSessionBean.setTask_session_department("CSE");
				taskSessionBean.setTask_session_class("BE");
				taskSessionBean.setTask_session_date("06/04/2016");
				taskSessionBean.setTask_session_subject("DataBase");

				dbAdapter.addTaskSession(taskSessionBean);
				Log.d("add", "inserted");

				ArrayList<TaskSessionBean> taskSessionBeanList = dbAdapter.getAllTaskSession();

				for(TaskSessionBean sessionBean : taskSessionBeanList)
				{
					Log.d("for", "in for loop");
					int tid = sessionBean.getTask_session_id();
					int did = sessionBean.getTask_session_developer_id();
					String sclass = sessionBean.getTask_session_class();
					String dept = sessionBean.getTask_session_department();
					String date=  sessionBean.getTask_session_date();
					String sub= sessionBean.getTask_session_subject();
					Log.d("id", tid+"");
					Log.d("did", did+"");
					Log.d("sclass", sclass);
					Log.d("dept",dept);
					Log.d("date", date);
					Log.d("sub", sub);
				}
			}
		});
	}
}
