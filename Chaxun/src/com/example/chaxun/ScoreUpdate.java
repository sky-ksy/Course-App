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

public class ScoreUpdate extends Activity {
	DatabaseHelper dbHelper;

	private EditText ucdscnumIn, ucdcoursenumIn, ucdscoreIn, uctscoreIn;
	private Button uscoreConfirm, uscoreCancel;

	private String ucdscnum, ucdcoursenum, ucdscore, uctscore, selection, usselection;
	private String[] selectionArgs;
	private List<String> usselArgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_update);

		dbHelper = new DatabaseHelper(this, "system", 1);
		usselArgs = new ArrayList<String>();
		ucdscnumIn = (EditText) findViewById(R.id.updcondition_scnumin);
		ucdcoursenumIn = (EditText) findViewById(R.id.updcondition_coursenumin);
		ucdscoreIn = (EditText) findViewById(R.id.updcondition_scorein);

		uctscoreIn = (EditText) findViewById(R.id.updcontent_scorein);

		uscoreConfirm = (Button) findViewById(R.id.updscoreConfirm);
		uscoreCancel = (Button) findViewById(R.id.updscoreCancel);

		ucdscnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdscnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		ucdcoursenumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdcoursenum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ucdscoreIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ucdscore = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		uctscoreIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				uctscore = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		uscoreConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				usselection = "";

				if (ucdscnum != null) {
					usselection = usselection + "Snum=?";
					usselArgs.add(ucdscnum);
				}
				if (ucdcoursenum != null) {
					if (usselection == "")
						usselection = usselection + "Cnum=?";
					else
						usselection = usselection + " and Cnum=?";
					usselArgs.add(ucdcoursenum);
				}
				if (ucdscore != null) {
					if (usselection == "")
						usselection = usselection + "Score=?";
					else
						usselection = usselection + " and Score=?";
					usselArgs.add(ucdscore);
				}

				selection = usselection;
				selectionArgs = usselArgs.toArray(new String[usselArgs.size()]);

				ContentValues cv = new ContentValues();
				if (uctscore != null)
					cv.put("Score", uctscore);

				updateScore("course", cv, selection, selectionArgs);
				Toast.makeText(ScoreUpdate.this, "更新成功！", Toast.LENGTH_LONG).show();

				/*
				 * Intent intent_basetoshow = new
				 * Intent(BaseQuery.this,ShowListb.class); Bundle mBundle = new
				 * Bundle(); mBundle.putSerializable(QB_KEY,qb);
				 * intent_basetoshow.putExtras(mBundle);
				 * startActivity(intent_basetoshow); BaseQuery.this.finish();
				 */

			}
		});
		uscoreCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_uscore = new Intent();
				intent_uscore.setClass(ScoreUpdate.this, MainActivity.class);
				startActivity(intent_uscore);
				ScoreUpdate.this.finish();
			}
		});

	}

	private void updateScore(String table, ContentValues values, String whereClause, String[] whereArgs) {
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
