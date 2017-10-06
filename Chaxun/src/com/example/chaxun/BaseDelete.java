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

public class BaseDelete extends Activity {
	DatabaseHelper dbHelper;
	private EditText delnumIn, delnameIn, delsexIn, delageIn, delphoneIn;
	private Button dbaseConfirm, dbaseCancel;
	private String dnum, dname, dsex, dage, dphone, selection,dbselection;
	private String[] selectionArgs;

	private List<String> dbselArgs;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_delete);
		dbHelper = new DatabaseHelper(this, "system", 1);
		dbselArgs=new ArrayList<String>();
		
		delnumIn = (EditText) findViewById(R.id.delcondition_numin);
		delnameIn = (EditText) findViewById(R.id.delcondition_namein);
		delsexIn = (EditText) findViewById(R.id.delcondition_sexin);
		delageIn = (EditText) findViewById(R.id.delcondition_agein);
		delphoneIn = (EditText) findViewById(R.id.delcondition_phonein);
		dbaseConfirm = (Button) findViewById(R.id.delbaseConfirm);
		dbaseCancel = (Button) findViewById(R.id.delbaseCancel);

		delnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		delnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		delsexIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dsex = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		delageIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dage = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		delphoneIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dphone = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		dbaseConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dbselection = "";

				if (dnum != null) {
					dbselection = dbselection + "Snum=?";
					dbselArgs.add(dnum);
				}
				if (dname != null) {
					if (dbselection == "")
						dbselection = dbselection + "Sname=?";
					else
						dbselection = dbselection + " and Sname=?";
					dbselArgs.add(dname);
				}
				if (dsex != null) {
					if (dbselection == "")
						dbselection = dbselection + "Ssex=?";
					else
						dbselection = dbselection + " and Ssex=?";
					dbselArgs.add(dsex);
				}
				if (dage != null) {
					
					if (dbselection == "")
						dbselection = dbselection + "Sage=?";
					else {

						dbselection = dbselection + " and Sage=?";
					}
					dbselArgs.add(dage);
				}
				if (dphone != null) {
					if (dbselection == "")
						dbselection = dbselection + "Sphone=?";
					else
						dbselection = dbselection + " and Sphone=?";
					dbselArgs.add(dphone);
				}

				selection = dbselection;
				selectionArgs = dbselArgs.toArray(new String[dbselArgs.size()]);

				deleteStudent("student", selection, selectionArgs);
				Toast.makeText(BaseDelete.this, "É¾³ý³É¹¦£¡", Toast.LENGTH_LONG).show();
			}
		});
		dbaseCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_dbase = new Intent();
				intent_dbase.setClass(BaseDelete.this, MainActivity.class);
				startActivity(intent_dbase);
				BaseDelete.this.finish();
			}
		});
	}

	private void deleteStudent(String table, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.delete(table, whereClause, whereArgs);
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
