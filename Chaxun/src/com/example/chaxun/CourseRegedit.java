package com.example.chaxun;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CourseRegedit extends Activity{
	DatabaseHelper dbHelper;
	SQLiteDatabase db;
	private EditText cnumIn,cnameIn,creditIn;
	private Button courseConfirm,courseCancel;
	private String cname,cnum,credit;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_regedit);
		dbHelper=new DatabaseHelper(this,"system",1);
		cnumIn=(EditText)findViewById(R.id.cnumin);
		cnameIn=(EditText)findViewById(R.id.cnamein);
		creditIn=(EditText)findViewById(R.id.creditin);
		courseConfirm=(Button)findViewById(R.id.course_confirm);
		courseCancel=(Button)findViewById(R.id.course_cancel);
		/*insertCourse("C001", "���ݿ�", "3.5");
		insertCourse("C002", "C����", "4");
		insertCourse("C003", "��������ԭ��", "3");
		insertCourse("C004", "�Զ�����ԭ��", "2");
		insertCourse("C005", "���ݽṹ", "4");*/
		cnumIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				cnum=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		cnameIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				cname=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		creditIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				credit=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		courseConfirm.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(cnum==null){
					Toast.makeText(CourseRegedit.this, "�γ̺Ų���Ϊ��",Toast.LENGTH_SHORT).show();
				    return;
				}
				if(cname==null){
					Toast.makeText(CourseRegedit.this, "�γ�������Ϊ��",Toast.LENGTH_SHORT).show();
				    return;
				}
				int a = dbHelper.getReadableDatabase().rawQuery("select *from course where Cnum=?", new String[] { cnum }).getCount();
				if(a==0){
					insertCourse(cnum,cname,credit);
					Toast.makeText(CourseRegedit.this, "����ɹ���", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(CourseRegedit.this, "������ͬ�γ̺ţ�", Toast.LENGTH_LONG).show();
					return;
				}
				

			}
		});
		courseCancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent_course=new Intent(CourseRegedit.this, MainActivity.class);
				startActivity(intent_course);
				CourseRegedit.this.finish();
			}
		});
	}
	
	private void insertCourse(String Cnum, String Cname, String Ccredit) {
		db = dbHelper.getReadableDatabase();
		db.execSQL("insert into course values(?,?,?)", new Object[]{Cnum,Cname,Ccredit});
		db.close();
	}
}
