package com.example.chaxun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
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

public class CourseUpdate extends Activity {
	DatabaseHelper dbHelper;

	private EditText ucdcnumIn, ucdcnameIn, ucdcreditIn, uctcnameIn, uctcreditIn;
	private Button ucourseConfirm, ucourseCancel;

	private String ucdcnum, ucdcname, ucdcredit, uctcname, uctcredit, selection, ucselection;
	private String[] selectionArgs;
	private List<String> ucselArgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_update);

		dbHelper = new DatabaseHelper(this, "system", 1);
		ucselArgs = new ArrayList<String>();
		ucdcnumIn = (EditText) findViewById(R.id.updcondition_cnumin);
		ucdcnameIn = (EditText) findViewById(R.id.updcondition_cnamein);
		ucdcreditIn = (EditText) findViewById(R.id.updcondition_creditin);

		uctcnameIn = (EditText) findViewById(R.id.updcontent_cnamein);
		uctcreditIn = (EditText) findViewById(R.id.updcontent_creditin);

		ucourseConfirm = (Button) findViewById(R.id.updcourseConfirm);
		ucourseCancel = (Button) findViewById(R.id.updcourseCancel);

		ucdcnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdcnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		ucdcnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdcname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ucdcreditIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdcredit = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		uctcnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctcname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		uctcreditIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctcredit = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		ucourseConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ucselection = "";
				if (ucdcnum != null) {
					ucselection = ucselection + "Cnum=?";
					ucselArgs.add(ucdcnum);
				}
				if (ucdcname != null) {
					if (ucselection == "")
						ucselection = ucselection + "Cname=?";
					else
						ucselection = ucselection + " and Cname=?";
					ucselArgs.add(ucdcname);
				}
				if (ucdcredit != null) {
					if (ucselection == "")
						ucselection = ucselection + "Ccredit=?";
					else
						ucselection = ucselection + " and Ccredit=?";
					ucselArgs.add(ucdcredit);
				}

				selection = ucselection;
				selectionArgs = ucselArgs.toArray(new String[ucselArgs.size()]);

				ContentValues cv = new ContentValues();
				if (uctcname != null)
					cv.put("Cname", uctcname);
				if (uctcredit != null)
					cv.put("Ccredit", uctcredit);
				updateCourse("course", cv, selection, selectionArgs);
				Toast.makeText(CourseUpdate.this, "更新成功！", Toast.LENGTH_LONG).show();
				}
		});
		ucourseCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_ucourse = new Intent();
				intent_ucourse.setClass(CourseUpdate.this, MainActivity.class);
				startActivity(intent_ucourse);
				CourseUpdate.this.finish();
			}
		});

	}

	private void updateCourse(String table, ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.update(table, values, whereClause, whereArgs);
		db.close();
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
