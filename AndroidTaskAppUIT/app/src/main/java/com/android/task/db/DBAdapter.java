package com.android.task.db;

import java.util.ArrayList;

import com.android.task.bean.TaskBean;
import com.android.task.bean.TaskSessionBean;
import com.android.task.bean.DeveloperBean;
import com.android.task.bean.ProjectBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Tasks";

	// Contacts table name
	private static final String DEVELOPER_INFO_TABLE = "developer_table";
	private static final String PROJECT_INFO_TABLE = "project_table";
	private static final String TASK_SESSION_TABLE = "task_session_table";
	private static final String TASK_TABLE = "task_table";


	// Contacts Table Columns names
	private static final String KEY_DEVELOPER_ID = "developer_id";
	private static final String KEY_DEVELOPER_FIRSTNAME = "developer_firstname";
	private static final String KEY_DEVELOPER_LASTNAME = "developer_Lastname";
	private static final String KEY_DEVELOPER_MO_NO = "developer_mobilenumber";
	private static final String KEY_DEVELOPER_ADDRESS = "developer_address";
	private static final String KEY_DEVELOPER_USERNAME = "developer_username";
	private static final String KEY_DEVELOPER_PASSWORD = "developer_password";

	private static final String KEY_PROJECT_ID = "project_id";
	private static final String KEY_PROJECT_NAME = "project_name";
	private static final String KEY_PROJECT_DESCRIPTION = "project_description";

	private static final String KEY_PROJECT_MO_NO = "project_mobilenumber";
	private static final String KEY_PROJECT_ADDRESS = "project_address";
	private static final String KEY_PROJECT_DEPARTMENT = "project_department";
	private static final String KEY_PROJECT_CLASS = "project_class";

	private static final String KEY_TASK_SESSION_ID = "task_session_id";
	private static final String KEY_TASK_SESSION_DEVELOPER_ID = "task_session_developer_id";
	private static final String KEY_TASK_SESSION_DEPARTMENT = "task_session_department";
	private static final String KEY_TASK_SESSION_CLASS = "task_session_class";
	private static final String KEY_TASK_SESSION_DATE = "task_session_date";
	private static final String KEY_TASK_SESSION_SUBJECT = "task_session_subject";

	private static final String KEY_SESSION_ID = "task_session_id";
	private static final String KEY_TASK_DEVELOPER_ID = "task_developer_id";
	private static final String KEY_TASK_STATUS = "task_status";


	public DBAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override

	public void onCreate(SQLiteDatabase db) {
		String queryDeveloper="CREATE TABLE "+ DEVELOPER_INFO_TABLE +" (" +
				KEY_DEVELOPER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_DEVELOPER_FIRSTNAME + " TEXT, " +
				KEY_DEVELOPER_LASTNAME + " TEXT, " +
				KEY_DEVELOPER_MO_NO + " TEXT, " +
				KEY_DEVELOPER_ADDRESS + " TEXT," +
				KEY_DEVELOPER_USERNAME + " TEXT," +
				KEY_DEVELOPER_PASSWORD + " TEXT " + ")";
		Log.d("queryDeveloper",queryDeveloper);


		String queryProject="CREATE TABLE "+ PROJECT_INFO_TABLE +" (" +
				KEY_PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_PROJECT_NAME + " TEXT, " +
				KEY_PROJECT_DESCRIPTION + " TEXT, " +
				KEY_PROJECT_MO_NO + " TEXT, " +
				KEY_PROJECT_ADDRESS + " TEXT," +
				KEY_PROJECT_DEPARTMENT + " TEXT," +
				KEY_PROJECT_CLASS + " TEXT " + ")";
		Log.d("queryProject",queryProject );


		String queryTaskSession="CREATE TABLE "+ TASK_SESSION_TABLE +" (" +
				KEY_TASK_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_TASK_SESSION_DEVELOPER_ID + " INTEGER, " +
				KEY_TASK_SESSION_DEPARTMENT + " TEXT, " +
				KEY_TASK_SESSION_CLASS + " TEXT, " +
				KEY_TASK_SESSION_DATE + " DATE," +
				KEY_TASK_SESSION_SUBJECT + " TEXT" + ")";
		Log.d("queryTaskSession",queryTaskSession );


		String queryTask="CREATE TABLE "+ TASK_TABLE +" (" +
				KEY_SESSION_ID + " INTEGER, " +
				KEY_TASK_DEVELOPER_ID + " INTEGER, " +
				KEY_TASK_STATUS + " TEXT " + ")";
		Log.d("queryTask",queryTask );


		try
		{
			db.execSQL(queryDeveloper);
			db.execSQL(queryProject);
			db.execSQL(queryTaskSession);
			db.execSQL(queryTask);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.e("Exception", e.getMessage());
		}

	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String queryDeveloper="CREATE TABLE "+ DEVELOPER_INFO_TABLE +" (" +
				KEY_DEVELOPER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_DEVELOPER_FIRSTNAME + " TEXT, " +
				KEY_DEVELOPER_LASTNAME + " TEXT, " +
				KEY_DEVELOPER_MO_NO + " TEXT, " +
				KEY_DEVELOPER_ADDRESS + " TEXT," +
				KEY_DEVELOPER_USERNAME + " TEXT," +
				KEY_DEVELOPER_PASSWORD + " TEXT " + ")";
		Log.d("queryDeveloper",queryDeveloper);


		String queryProject="CREATE TABLE "+ PROJECT_INFO_TABLE +" (" +
				KEY_PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_PROJECT_NAME + " TEXT, " +
				KEY_PROJECT_DESCRIPTION + " TEXT, " +
				KEY_PROJECT_MO_NO + " TEXT, " +
				KEY_PROJECT_ADDRESS + " TEXT," +
				KEY_PROJECT_DEPARTMENT + " TEXT," +
				KEY_PROJECT_CLASS + " TEXT " + ")";
		Log.d("queryProject",queryProject );


		String queryTaskSession="CREATE TABLE "+ TASK_SESSION_TABLE +" (" +
				KEY_TASK_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_TASK_SESSION_DEVELOPER_ID + " INTEGER, " +
				KEY_TASK_SESSION_DEPARTMENT + " TEXT, " +
				KEY_TASK_SESSION_CLASS + " TEXT, " +
				KEY_TASK_SESSION_DATE + " TEXT," +
				KEY_TASK_SESSION_SUBJECT + " TEXT" +")";
		Log.d("queryTaskSession",queryTaskSession );


		String queryTask="CREATE TABLE "+ TASK_TABLE +" (" +
				KEY_SESSION_ID + " INTEGER, " +
				KEY_TASK_DEVELOPER_ID + " INTEGER, " +
				KEY_TASK_STATUS + " TEXT " + ")";
		Log.d("queryTask",queryTask );

		try
		{
			db.execSQL(queryDeveloper);
			db.execSQL(queryProject);
			db.execSQL(queryTaskSession);
			db.execSQL(queryTask);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.e("Exception", e.getMessage());
		}
	}

	//developer crud
	public void addDeveloper(DeveloperBean developerBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO developer_table (developer_firstname,developer_Lastname,developer_mobilenumber,developer_address,developer_username,developer_password) values ('"+
				developerBean.getDeveloper_firstname()+"', '"+
				developerBean.getDeveloper_lastname()+"', '"+
				developerBean.getDeveloper_mobilenumber()+"', '"+

				developerBean.getDeveloper_address()+"', '"+
				developerBean.getDeveloper_username()+"', '"+
				developerBean.getDeveloper_password()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	public DeveloperBean validateDeveloper(String userName,String password)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM developer_table where developer_username='"+userName+"' and developer_password='"+password+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{

			DeveloperBean developerBean = new DeveloperBean();
			developerBean.setDeveloper_id(Integer.parseInt(cursor.getString(0)));
			developerBean.setDeveloper_firstname(cursor.getString(1));
			developerBean.setDeveloper_lastname(cursor.getString(2));
			developerBean.setDeveloper_mobilenumber(cursor.getString(3));
			developerBean.setDeveloper_address(cursor.getString(4));
			developerBean.setDeveloper_username(cursor.getString(5));
			developerBean.setDeveloper_password(cursor.getString(6));
			return developerBean;
		}
		return null;
	}

	public ArrayList<DeveloperBean> getAllDeveloper()
	{
		Log.d("in get all","in get all" );
		ArrayList<DeveloperBean> list = new ArrayList<DeveloperBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM developer_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				DeveloperBean developerBean = new DeveloperBean();
				developerBean.setDeveloper_id(Integer.parseInt(cursor.getString(0)));
				developerBean.setDeveloper_firstname(cursor.getString(1));
				developerBean.setDeveloper_lastname(cursor.getString(2));
				developerBean.setDeveloper_mobilenumber(cursor.getString(3));
				developerBean.setDeveloper_address(cursor.getString(4));
				developerBean.setDeveloper_username(cursor.getString(5));
				developerBean.setDeveloper_password(cursor.getString(6));
				list.add(developerBean);

			}while(cursor.moveToNext());
		}
		return list;
	}

	public void deleteDeveloper(int developerId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM developer_table WHERE developer_id="+developerId ;

		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	//project crud
	public void addProject(ProjectBean projectBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO project_table (project_name, project_description, project_mobilenumber, project_address, project_department, project_class) values ('" +
				projectBean.getProject_name() + "', '" +
				projectBean.getProject_description() + "','" +
				projectBean.getProject_mobilenumber() + "', '" +
				projectBean.getProject_address() + "', '" +
				projectBean.getProject_department() + "', '" +
				projectBean.getProject_class() + "')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	public ArrayList<ProjectBean> getAllProject()
	{
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM project_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				ProjectBean projectBean = new ProjectBean();
				projectBean.setProject_id(Integer.parseInt(cursor.getString(0)));
				projectBean.setProject_name(cursor.getString(1));
				projectBean.setProject_description(cursor.getString(2));
				projectBean.setProject_mobilenumber(cursor.getString(3));
				projectBean.setProject_address(cursor.getString(4));
				projectBean.setProject_department(cursor.getString(5));
				projectBean.setProject_class(cursor.getString(6));
				list.add(projectBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public ArrayList<ProjectBean> getAllProjectByBranchYear(String branch,String year)
	{
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM project_table where project_department='"+branch+"' and project_class='"+year+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				ProjectBean projectBean = new ProjectBean();
				projectBean.setProject_id(Integer.parseInt(cursor.getString(0)));
				projectBean.setProject_name(cursor.getString(1));
				projectBean.setProject_description(cursor.getString(2));
				projectBean.setProject_mobilenumber(cursor.getString(3));
				projectBean.setProject_address(cursor.getString(4));
				projectBean.setProject_department(cursor.getString(5));
				projectBean.setProject_class(cursor.getString(6));
				list.add(projectBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public ProjectBean getProjectById(int projectId)
	{
		ProjectBean projectBean = new ProjectBean();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM project_table where project_id="+projectId;
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{

				projectBean.setProject_id(Integer.parseInt(cursor.getString(0)));
				projectBean.setProject_name(cursor.getString(1));
				projectBean.setProject_description(cursor.getString(2));
				projectBean.setProject_mobilenumber(cursor.getString(3));
				projectBean.setProject_address(cursor.getString(4));
				projectBean.setProject_department(cursor.getString(5));
				projectBean.setProject_class(cursor.getString(6));

			}while(cursor.moveToNext());
		}
		return projectBean;
	}

	public void deleteProject(int projectId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM project_table WHERE project_id="+projectId ;

		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	//task session Table crud
	public int addTaskSession(TaskSessionBean taskSessionBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO task_session_table (task_session_developer_id,task_session_department,task_session_class,task_session_date,task_session_subject) values ('"+
				taskSessionBean.getTask_session_developer_id()+"', '"+
				taskSessionBean.getTask_session_department()+"','"+
				taskSessionBean.getTask_session_class()+"', '"+
				taskSessionBean.getTask_session_date()+"', '"+
				taskSessionBean.getTask_session_subject()+"')";
		Log.d("query", query);
		db.execSQL(query);

		String query1= "select max(task_session_id) from task_session_table";
		Cursor cursor = db.rawQuery(query1, null);

		if(cursor.moveToFirst())
		{
			int sessionId = Integer.parseInt(cursor.getString(0));

			return sessionId;
		}


		db.close();
		return 0;
	}

	public ArrayList<TaskSessionBean> getAllTaskSession()
	{
		ArrayList<TaskSessionBean> list = new ArrayList<TaskSessionBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM task_session_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				TaskSessionBean taskSessionBean = new TaskSessionBean();
				taskSessionBean.setTask_session_id(Integer.parseInt(cursor.getString(0)));
				taskSessionBean.setTask_session_developer_id(Integer.parseInt(cursor.getString(1)));
				taskSessionBean.setTask_session_department(cursor.getString(2));
				taskSessionBean.setTask_session_class(cursor.getString(3));
				taskSessionBean.setTask_session_date(cursor.getString(4));
				taskSessionBean.setTask_session_subject(cursor.getString(5));
				list.add(taskSessionBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public void deleteTaskSession(int taskSessionId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM task_session_table WHERE task_session_id="+taskSessionId ;

		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	//task crud
	public void addNewTask(TaskBean taskBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO task_table values ("+
				taskBean.getTask_session_id()+", "+
				taskBean.getTask_project_id()+", '"+
				taskBean.getTask_status()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	public ArrayList<TaskBean> getTaskBySessionID(TaskSessionBean taskSessionBean)
	{
		int taskSessionId=0;
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM task_session_table where task_session_developer_id="+taskSessionBean.getTask_session_developer_id()+""
				+" AND task_session_department='"+taskSessionBean.getTask_session_department()+"' AND task_session_class='"+taskSessionBean.getTask_session_class()+"'" +
				" AND task_session_date='"+taskSessionBean.getTask_session_date()+"' AND task_session_subject='"+taskSessionBean.getTask_session_subject()+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				taskSessionId=(Integer.parseInt(cursor.getString(0)));
			}while(cursor.moveToNext());
		}

		String query1="SELECT * FROM task_table where task_session_id=" + taskSessionId+" order by task_project_id";
		Cursor cursor1 = db.rawQuery(query1, null);
		if(cursor1.moveToFirst())
		{
			do{
				TaskBean taskBean = new TaskBean();
				taskBean.setTask_session_id(Integer.parseInt(cursor1.getString(0)));
				taskBean.setTask_project_id(Integer.parseInt(cursor1.getString(1)));
				taskBean.setTask_status(cursor1.getString(2));
				list.add(taskBean);

			}while(cursor1.moveToNext());
		}
		return list;
	}



	public ArrayList<TaskBean> getTotalTaskBySessionID(TaskSessionBean taskSessionBean)
	{
		int taskSessionId=0;
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM task_session_table where task_session_developer_id="+taskSessionBean.getTask_session_developer_id()+""
				+" AND task_session_department='"+taskSessionBean.getTask_session_department()+"' AND task_session_class='"+taskSessionBean.getTask_session_class()+"'" +
				" AND task_session_subject='"+taskSessionBean.getTask_session_subject()+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				taskSessionId=(Integer.parseInt(cursor.getString(0)));

				String query1="SELECT * FROM task_table where task_session_id=" + taskSessionId+" order by task_project_id";
				Cursor cursor1 = db.rawQuery(query1, null);
				if(cursor1.moveToFirst())
				{
					do{
						TaskBean taskBean = new TaskBean();
						taskBean.setTask_session_id(Integer.parseInt(cursor1.getString(0)));
						taskBean.setTask_project_id(Integer.parseInt(cursor1.getString(1)));
						taskBean.setTask_status(cursor1.getString(2));
						list.add(taskBean);

					}while(cursor1.moveToNext());
				}

				TaskBean taskBean = new TaskBean();
				taskBean.setTask_session_id(0);
				taskBean.setTask_status("Date : " + cursor.getString(4));
				list.add(taskBean);

			}while(cursor.moveToNext());
		}


		return list;
	}


	public ArrayList<TaskBean> getAllTaskByProject()
	{
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT project_id, count(*) FROM project_table WHERE project_status='P' GROUP BY project_id";

		Log.d("query", query);

		Cursor cursor = db.rawQuery(query, null);




		if(cursor.moveToFirst())
		{
			do{
				Log.d("projectId","projectId:"+cursor.getString(0)+", Count:"+cursor.getString(1));
				TaskBean taskBean = new TaskBean();
				taskBean.setTask_project_id(Integer.parseInt(cursor.getString(0)));
				taskBean.setTask_session_id(Integer.parseInt(cursor.getString(1)));
				list.add(taskBean);

			}while(cursor.moveToNext());
		}
		return list;
	}
}
