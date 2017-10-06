package com.example.chaxun;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CourseDelete extends Activity {
	DatabaseHelper dbHelper;
	private EditText delcnumIn,delcnameIn,delcreditIn;
	private Button dcourseConfirm,dcourseCancel;
	private String dcnum,dcname,dcredit,selection,dcselection;
	private String[] selectionArgs;
	private List<String> dcselArgs;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_delete);
		dbHelper=new DatabaseHelper(this,"system",1);
		dcselArgs=new ArrayList<String>();
		
		delcnumIn=(EditText)findViewById(R.id.delcondition_cnumin);
		delcnameIn=(EditText)findViewById(R.id.delcondition_cnamein);
		delcreditIn=(EditText)findViewById(R.id.delcondition_creditin);
		
		dcourseConfirm=(Button)findViewById(R.id.delcourseConfirm);
		dcourseCancel=(Button)findViewById(R.id.delcourseCancel);
		
		delcnumIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dcnum=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		delcnameIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dcname=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		delcreditIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dcredit=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		
		dcourseConfirm.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dcselection = "";
				if (dcnum != null) {
					dcselection = dcselection + "Cnum=?";
					dcselArgs.add(dcnum);
				}
				if (dcname != null) {
					if (dcselection == "")
						dcselection = dcselection + "Cname=?";
					else
						dcselection = dcselection + " and Cname=?";
					dcselArgs.add(dcname);
				}
				if (dcredit != null) {
					if (dcselection == "")
						dcselection = dcselection + "Ccredit=?";
					else
						dcselection = dcselection + " and Ccredit=?";
					dcselArgs.add(dcredit);
				}
				
				
				selection = dcselection;
				selectionArgs = dcselArgs.toArray(new String[dcselArgs.size()]);
				Log.v("tag", selection+selectionArgs);
				deleteCourse("course",selection,selectionArgs);
				Toast.makeText(CourseDelete.this, "É¾³ý³É¹¦£¡", Toast.LENGTH_LONG).show();
			}
		});
		dcourseCancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent_dcourse=new Intent();
				intent_dcourse.setClass(CourseDelete.this, MainActivity.class);
				startActivity(intent_dcourse);
				CourseDelete.this.finish();
			}
		});
	}
	
	private void deleteCourse(String table, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.delete(table,whereClause,whereArgs);
		db.close();
	}

	

	/*private void queryStudent(String table, String[] columns, String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, null);
		db.close();
	}*/
}
