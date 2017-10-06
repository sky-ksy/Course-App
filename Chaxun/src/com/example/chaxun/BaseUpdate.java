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

public class BaseUpdate extends Activity {
	DatabaseHelper dbHelper;

	private EditText ucdnumIn, ucdnameIn, ucdsexIn, ucdageIn, ucdphoneIn, uctnameIn, uctsexIn, uctageIn, uctphoneIn;
	private Button ubaseConfirm, ubaseCancel;

	private String ucdnum, ucdname, ucdsex, ucdage, ucdphone, uctname, uctsex, uctage, uctphone, selection, ubselection;
	private String[] selectionArgs;
	private List<String> ubselArgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_update);

		dbHelper = new DatabaseHelper(this, "system", 1);
		ubselArgs = new ArrayList<String>();
		ucdnumIn = (EditText) findViewById(R.id.updcondition_numin);
		ucdnameIn = (EditText) findViewById(R.id.updcondition_namein);
		ucdsexIn = (EditText) findViewById(R.id.updcondition_sexin);
		ucdageIn = (EditText) findViewById(R.id.updcondition_agein);
		ucdphoneIn = (EditText) findViewById(R.id.updcondition_phonein);

		uctnameIn = (EditText) findViewById(R.id.updcontent_namein);
		uctsexIn = (EditText) findViewById(R.id.updcontent_sexin);
		uctageIn = (EditText) findViewById(R.id.updcontent_agein);
		uctphoneIn = (EditText) findViewById(R.id.updcontent_phonein);

		ubaseConfirm = (Button) findViewById(R.id.updbaseConfirm);
		ubaseCancel = (Button) findViewById(R.id.updbaseCancel);

		ucdnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		ucdnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ucdsexIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdsex = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ucdageIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdage = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ucdphoneIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdphone = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		uctnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		uctsexIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctsex = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		uctageIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctage = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		uctphoneIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctphone = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ubaseConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ubselection = "";

				if (ucdnum != null) {
					ubselection = ubselection + "Snum=?";
					ubselArgs.add(ucdnum);
				}
				if (ucdname != null) {
					if (ubselection == "")
						ubselection = ubselection + "Sname=?";
					else
						ubselection = ubselection + " and Sname=?";
					ubselArgs.add(ucdname);
				}
				if (ucdsex != null) {
					if (ubselection == "")
						ubselection = ubselection + "Ssex=?";
					else
						ubselection = ubselection + " and Ssex=?";
					ubselArgs.add(ucdsex);
				}
				if (ucdage != null) {

					if (ubselection == "")
						ubselection = ubselection + "Sage=?";
					else {

						ubselection = ubselection + " and Sage=?";
					}
					ubselArgs.add(ucdage);
				}
				if (ucdphone != null) {
					if (ubselection == "")
						ubselection = ubselection + "Sphone=?";
					else
						ubselection = ubselection + " and Sphone=?";
					ubselArgs.add(ucdphone);
				}

				selection = ubselection;
				selectionArgs = ubselArgs.toArray(new String[ubselArgs.size()]);

				ContentValues cv = new ContentValues();
				if (uctname != null)
					cv.put("Sname", uctname);
				if (uctsex != null)
					cv.put("Ssex", uctsex);
				if (uctage != null)
					cv.put("Sage", uctage);
				if (uctphone != null)
					cv.put("Sphone", uctphone);
				updateStudent("student", cv, selection, selectionArgs);
				Toast.makeText(BaseUpdate.this, "更新成功！", Toast.LENGTH_LONG).show();

				/*
				 * Intent intent_basetoshow = new
				 * Intent(BaseQuery.this,ShowListb.class); Bundle mBundle = new
				 * Bundle(); mBundle.putSerializable(QB_KEY,qb);
				 * intent_basetoshow.putExtras(mBundle);
				 * startActivity(intent_basetoshow); BaseQuery.this.finish();
				 */

			}
		});
		ubaseCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_ubase = new Intent();
				intent_ubase.setClass(BaseUpdate.this, MainActivity.class);
				startActivity(intent_ubase);
				BaseUpdate.this.finish();
			}
		});

	}

	private void updateStudent(String table, ContentValues values, String whereClause, String[] whereArgs) {
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
