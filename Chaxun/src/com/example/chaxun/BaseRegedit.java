package com.example.chaxun;

import android.app.Activity;
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

public class BaseRegedit extends Activity {
	DatabaseHelper dbHelper;
	SQLiteDatabase db;
	private EditText numIn, nameIn, sexIn, ageIn, phoneIn;
	private Button baseConfirm, baseCancel;
	private String num, name, sex, age, phone;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_regedit);
		dbHelper = new DatabaseHelper(this, "system", 1);
		numIn = (EditText) findViewById(R.id.numin);
		nameIn = (EditText) findViewById(R.id.namein);
		sexIn = (EditText) findViewById(R.id.sexin);
		ageIn = (EditText) findViewById(R.id.agein);
		phoneIn = (EditText) findViewById(R.id.phonein);
		baseConfirm = (Button) findViewById(R.id.base_confirm);
		baseCancel = (Button) findViewById(R.id.base_cancel);
		
		 /*insertStudent("33001", "����ɽ","��","18","45673434");
		 insertStudent("33002", "���","��","22","89457321");
		 insertStudent("33003", "�����","Ů","17",null); 
		 insertStudent("33004","������","Ů","17","18814325768"); 
		 insertStudent("33005","������","��","24","13098765892"); 
		 insertStudent("33006","������","Ů","16",null);*/
		 
		numIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				num = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		nameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				name = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		sexIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				sex = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ageIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				age = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		phoneIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				phone = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		baseConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (num == null) {
					Toast.makeText(BaseRegedit.this, "ѧ�Ų���Ϊ��", Toast.LENGTH_SHORT).show();
					return;
				}
				if (name == null) {
					Toast.makeText(BaseRegedit.this, "��������Ϊ��", Toast.LENGTH_SHORT).show();
					return;
				}
				int a = dbHelper.getReadableDatabase().rawQuery("select *from student where Snum=?", new String[] { num }).getCount();
				if (a == 0) {
					insertStudent(num, name, sex, age, phone);
					Toast.makeText(BaseRegedit.this, "����ɹ���", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(BaseRegedit.this, "������ͬѧ�ţ�", Toast.LENGTH_LONG).show();
					return;
				}
			}
		});
		baseCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_base = new Intent();
				intent_base.setClass(BaseRegedit.this, MainActivity.class);
				startActivity(intent_base);
				BaseRegedit.this.finish();
			}
		});
	}

	private void insertStudent(String Snum, String Sname, String Ssex, String Sage, String Sphone) {
		db = dbHelper.getReadableDatabase();
		db.execSQL("insert into student values(?,?,?,?,?)", new Object[] { Snum, Sname, Ssex, Sage, Sphone });
		db.close();
	}

	/*
	 * private void queryStudent(String table, String[] columns, String
	 * selection, String[] selectionArgs, String groupBy, String having, String
	 * orderBy, String limit) { SQLiteDatabase db =
	 * dbHelper.getReadableDatabase(); db.query(table, columns, selection,
	 * selectionArgs, groupBy, having, orderBy, null); db.close(); }
	 */
}
