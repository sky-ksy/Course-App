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

class Querys implements Serializable{
	String[] columns;
	String[] selectionArgs;
	String selection;
	public Querys(){
		columns=null;
		selectionArgs=null;
		selection=null;
	}
}
public class ScoreQuery extends Activity{
	DatabaseHelper dbHelper;
	Querys qs;
	private EditText qscnumIn, qcoursenumIn, qscoreIn;
	private Button qscoreConfirm, qscoreCancel;
	private CheckBox checkScnum, checkCoursenum, checkScore;
	private String qscnum, qcoursenum, qscore,qsselection;
	private List<String> qscolumn;
	private List<String> qsselArgs;
	public final static String QS_KEY = "com.example.qs";  
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_query);
		dbHelper=new DatabaseHelper(this,"system",1);
		qs=new Querys();
		qscolumn = new ArrayList<String>();
		qsselArgs = new ArrayList<String>();
		
		qscnumIn = (EditText) findViewById(R.id.qscnumin);
		qcoursenumIn = (EditText) findViewById(R.id.qcoursenumin);
		qscoreIn = (EditText) findViewById(R.id.qscorein);
		qscoreConfirm = (Button) findViewById(R.id.qscoreConfirm);
		qscoreCancel = (Button) findViewById(R.id.qscoreCancel);
		checkScnum = (CheckBox) findViewById(R.id.checkscnum);
		checkCoursenum = (CheckBox) findViewById(R.id.checkcoursenum);
		checkScore = (CheckBox) findViewById(R.id.checkscore);
		
		
		checkScnum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qscolumn.add("Snum");
				} else {
					if (qscolumn.contains("Snum"))
						qscolumn.remove("Snum");
				}
			}
		});
		checkCoursenum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qscolumn.add("Cnum");
				} else {
					if (qscolumn.contains("Cnum"))
						qscolumn.remove("Cnum");
				}
			}
		});
		checkScore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qscolumn.add("Score");
				} else {
					if (qscolumn.contains("Score"))
						qscolumn.remove("Score");
				}
			}
		});
		
		qscnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qscnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		qcoursenumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qcoursenum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		qscoreIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qscore = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		qscoreConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				qsselection = "";

				if (qscnum != null) {
					qsselection = qsselection + "Snum=?";
					qsselArgs.add(qscnum);
				}
				if (qcoursenum != null) {
					if (qsselection == "")
						qsselection = qsselection + "Cnum=?";
					else
						qsselection = qsselection + " and Cnum=?";
					qsselArgs.add(qcoursenum);
				}
				if (qscore != null) {
					if (qsselection == "")
						qsselection = qsselection + "Score=?";
					else
						qsselection = qsselection + " and Score=?";
					qsselArgs.add(qscore);
				}
				
				qs.columns = qscolumn.toArray(new String[qscolumn.size()]);
				qs.selection = qsselection;
				qs.selectionArgs = qsselArgs.toArray(new String[qsselArgs.size()]);
				
				Intent intent_scoretoshow = new Intent(ScoreQuery.this,ShowLists.class);  
		        Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(QS_KEY,qs);  
		        intent_scoretoshow.putExtras(mBundle);   
		        startActivity(intent_scoretoshow);  
		        ScoreQuery.this.finish();
				

			}
		});
		qscoreCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_qcourse = new Intent();
				intent_qcourse.setClass(ScoreQuery.this, MainActivity.class);
				startActivity(intent_qcourse);
				ScoreQuery.this.finish();
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
