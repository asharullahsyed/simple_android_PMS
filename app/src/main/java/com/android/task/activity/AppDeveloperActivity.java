package com.android.task.activity;

import com.android.task.bean.DeveloperBean;
import com.android.task.db.DBAdapter;
import com.example.androidtasksystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDeveloperActivity extends Activity {

	Button registerButton;
	EditText textFirstName;
	EditText textLastName;
	EditText textEmail;
	EditText textContact;
	EditText textAddress;
	EditText textUsername;
	EditText textPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddeveloper);

		textFirstName = (EditText) findViewById(R.id.editTextFirstName);
		textLastName = (EditText) findViewById(R.id.editTextLastName);
		textContact = (EditText) findViewById(R.id.editTextPhone);
		textAddress = (EditText) findViewById(R.id.editTextaddr);
		textUsername = (EditText) findViewById(R.id.editTextUserName);
		textPassword = (EditText) findViewById(R.id.editTextPassword);
		registerButton = (Button) findViewById(R.id.RegisterButton);

		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String firstName = textFirstName.getText().toString();
				String lastName = textLastName.getText().toString();
				String phoneNo = textContact.getText().toString();
				String address = textAddress.getText().toString();
				String userName = textUsername.getText().toString();
				String passWord = textPassword.getText().toString();

				if (TextUtils.isEmpty(firstName)) {
					textFirstName.setError("Please enter first name");
				} else if (TextUtils.isEmpty(lastName)) {
					textLastName.setError("Please enter last name");
				} else if (TextUtils.isEmpty(phoneNo)) {
					textContact.setError("Please enter phone number");
				} else if (TextUtils.isEmpty(address)) {
					textAddress.setError("Please enter address");
				} else if (TextUtils.isEmpty(userName)) {
					textContact.setError("Please enter username");
				} else if (TextUtils.isEmpty(passWord)) {
					textAddress.setError("Please enter password");
				} else {
					DeveloperBean developerBean = new DeveloperBean();
					developerBean.setDeveloper_firstname(firstName);
					developerBean.setDeveloper_lastname(lastName);
					developerBean.setDeveloper_mobilenumber(phoneNo);
					developerBean.setDeveloper_address(address);
					developerBean.setDeveloper_username(userName);
					developerBean.setDeveloper_password(passWord);

					DBAdapter dbAdapter = new DBAdapter(AddDeveloperActivity.this);
					dbAdapter.addDeveloper(developerBean);

					Intent intent = new Intent(AddDeveloperActivity.this, MenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Developer added successfully", Toast.LENGTH_SHORT).show();
				}
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
