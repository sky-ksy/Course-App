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

class Queryb implements Serializable{
	String[] columns;
	String[] selectionArgs;
	String selection;
	public Queryb(){
		columns=null;
		selectionArgs=null;
		selection=null;
	}
}


public class BaseQuery extends Activity{
	DatabaseHelper dbHelper;
	Queryb qb;
	private EditText qnumIn, qnameIn, qsexIn, qageIn, qphoneIn;
	private Button qbaseConfirm, qbaseCancel;
	private CheckBox checkNum, checkName, checkSex, checkAge, checkPhone;
	private String qnum, qname, qsex, qage, qphone,qbselection;
	private List<String> qbcolumn;
	
	private List<String> qbselArgs;
	public final static String QB_KEY = "com.example.qb";  
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_query);
		dbHelper=new DatabaseHelper(this,"system",1);
		qb=new Queryb();
		qbcolumn = new ArrayList<String>();
		
		qbselArgs = new ArrayList<String>();
		
		qnumIn = (EditText) findViewById(R.id.qnumin);
		qnameIn = (EditText) findViewById(R.id.qnamein);
		qsexIn = (EditText) findViewById(R.id.qsexin);
		qageIn = (EditText) findViewById(R.id.qagein);
		qphoneIn = (EditText) findViewById(R.id.qphonein);
		qbaseConfirm = (Button) findViewById(R.id.qbaseConfirm);
		qbaseCancel = (Button) findViewById(R.id.qbaseCancel);
		checkNum = (CheckBox) findViewById(R.id.checknum);
		checkName = (CheckBox) findViewById(R.id.checkname);
		checkSex = (CheckBox) findViewById(R.id.checksex);
		checkAge = (CheckBox) findViewById(R.id.checkage);
		checkPhone = (CheckBox) findViewById(R.id.checkphone);
		
		checkNum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qbcolumn.add("Snum");
				} else {
					if (qbcolumn.contains("Snum"))
						qbcolumn.remove("Snum");
				}
			}
		});
		checkName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qbcolumn.add("Sname");
				} else {
					if (qbcolumn.contains("Sname"))
						qbcolumn.remove("Sname");
				}
			}
		});
		checkSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qbcolumn.add("Ssex");
				} else {
					if (qbcolumn.contains("Ssex"))
						qbcolumn.remove("Ssex");
				}
			}
		});
		checkAge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qbcolumn.add("Sage");
				} else {
					if (qbcolumn.contains("Sage"))
						qbcolumn.remove("Sage");
				}
			}
		});
		checkPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					qbcolumn.add("Sphone");
				} else {
					if (qbcolumn.contains("Sphone"))
						qbcolumn.remove("Sphone");
				}
			}
		});
		qnumIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qnum = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		qnameIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qname = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		qsexIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qsex = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		qageIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qage = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		qphoneIn.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				qphone = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		qbaseConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				qbselection ="";
				
				if (qnum != null) {
					qbselection=qbselection+"Snum=?";
					qbselArgs.add(qnum);
				}
				if (qname != null) {
					if (qbselection == "")
						qbselection=qbselection+"Sname=?";
					else
						qbselection=qbselection+" and Sname=?";
					qbselArgs.add(qname);
				}
				if (qsex != null) {
					if (qbselection == "")
						qbselection=qbselection+"Ssex=?";
					else
						qbselection=qbselection+" and Ssex=?";
					qbselArgs.add(qsex);
				}
				if (qage != null) {
					Log.v("tag", qbselection.toString());
					if (qbselection=="")
						qbselection=qbselection+"Sage=?";
					else{
						
						qbselection=qbselection+" and Sage=?";
					}
					qbselArgs.add(qage);
				}
				if (qphone != null) {
					if (qbselection == "")
						qbselection=qbselection+"Sphone=?";
					else
						qbselection=qbselection+" and Sphone=?";
					qbselArgs.add(qphone);
				}
				qb.columns = qbcolumn.toArray(new String[qbcolumn.size()]);
				qb.selection = qbselection;
				qb.selectionArgs = qbselArgs.toArray(new String[qbselArgs.size()]);
				
				Intent intent_basetoshow = new Intent(BaseQuery.this,ShowListb.class);  
		        Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(QB_KEY,qb);  
		        intent_basetoshow.putExtras(mBundle);   
		        startActivity(intent_basetoshow);  
		        BaseQuery.this.finish();
				

			}
		});
		qbaseCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent_qbase = new Intent();
				intent_qbase.setClass(BaseQuery.this, MainActivity.class);
				startActivity(intent_qbase);
				BaseQuery.this.finish();
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
