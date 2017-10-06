package com.example.chaxun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

class Queryc implements Serializable{
	String[] columns;
	String[] selectionArgs;
	String selection;
	public Queryc(){
		columns=null;
		selectionArgs=null;
		selection=null;
	}
}
public class CourseQuery extends Activity{
	DatabaseHelper dbHelper;
	Queryc qc;
	private EditText qcnumIn, qcnameIn, qcreditIn;
	private Button qcourseConfirm, qcourseCancel;
	private CheckBox checkCnum, checkCname, checkCredit;
	private String qcnum, qcname, qcredit,qcselection;
	private List<String> qccolumn;
	private List<String> qcselArgs;
	public final static String QC_KEY = "com.example.qc";  
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_query);
		dbHelper=new DatabaseHelper(this,"system",1);
		qc=new Queryc();
		qccolumn = new ArrayList<String>();
		qcselArgs = new ArrayList<String>();
		
		qcnumIn = (EditText) findViewById(R.id.qcnumin);
		qcnameIn = (EditText) findViewById(R.id.qcnamein);
		qcreditIn = (EditText) findViewById(R.id.qcreditin);
		qcourseConfirm = (Button) findViewById(R.id.qcourseConfirm);
		qcourseCancel = (Button) findViewById(R.id.qcourseCancel);
		checkCnum = (CheckBox) findViewById(R.id.checkcnum);
		checkCname = (CheckBox) findViewById(R.id.checkcname);
		checkCredit = (CheckBox) findViewById(R.id.checkcredit);
		
		
		checkCnum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qccolumn.add("Cnum");
				} else {
					if (qccolumn.contains("Cnum"))
						qccolumn.remove("Cnum");
				}
			}
		});
		checkCname.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qccolumn.add("Cname");
				} else {
					if (qccolumn.contains("Cname"))
						qccolumn.remove("Cname");
				}
			}
		});
		checkCredit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qccolumn.add("Ccredit");
				} else {
					if (qccolumn.contains("Ccredit"))
						qccolumn.remove("Ccredit");
				}
			}
		});
		
		qcnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qcnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		qcnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qcname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		qcreditIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qcredit = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		qcourseConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				qcselection="";
				if (qcnum != null) {
					qcselection = qcselection + "Cnum=?";
					qcselArgs.add(qcnum);
				}
				if (qcname != null) {
					if (qcselection == "")
						qcselection = qcselection + "Cname=?";
					else
						qcselection = qcselection + " and Cname=?";
					qcselArgs.add(qcname);
				}
				if (qcredit != null) {
					if (qcselection == "")
						qcselection = qcselection + "Ccredit=?";
					else
						qcselection = qcselection + " and Ccredit=?";
					qcselArgs.add(qcredit);
				}
				
				qc.columns = qccolumn.toArray(new String[qccolumn.size()]);
				qc.selection = qcselection;
				qc.selectionArgs = qcselArgs.toArray(new String[qcselArgs.size()]);
				
				Intent intent_coursetoshow = new Intent(CourseQuery.this,ShowListc.class);  
		        Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(QC_KEY,qc);  
		        intent_coursetoshow.putExtras(mBundle);   
		        startActivity(intent_coursetoshow);  
		        CourseQuery.this.finish();
				

			}
		});
		qcourseCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_qcourse = new Intent();
				intent_qcourse.setClass(CourseQuery.this, MainActivity.class);
				startActivity(intent_qcourse);
				CourseQuery.this.finish();
			}
		});
	}

	
		

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base_query, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
