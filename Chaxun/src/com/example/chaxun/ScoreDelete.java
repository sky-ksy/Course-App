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

public class ScoreDelete extends Activity {
	DatabaseHelper dbHelper;
	private EditText delscnumIn,delcoursenumIn,delscoreIn;
	private Button dscoreConfirm,dscoreCancel;
	private String dscnum,dcoursenum,dscore,selection,dsselection;
	private String[] selectionArgs;
	private List<String> dsselArgs;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_delete);
		dbHelper=new DatabaseHelper(this,"system",1);
		dsselArgs=new ArrayList<String>();
		
		delscnumIn=(EditText)findViewById(R.id.delcondition_scnumin);
		delcoursenumIn=(EditText)findViewById(R.id.delcondition_coursenumin);
		delscoreIn=(EditText)findViewById(R.id.delcondition_scorein);
		
		dscoreConfirm=(Button)findViewById(R.id.delscoreConfirm);
		dscoreCancel=(Button)findViewById(R.id.delscoreCancel);
		
		delscnumIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dscnum=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		delcoursenumIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dcoursenum=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		delscoreIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dscore=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		
		dscoreConfirm.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dsselection = "";

				if (dscnum != null) {
					dsselection = dsselection + "Snum=?";
					dsselArgs.add(dscnum);
				}
				if (dcoursenum != null) {
					if (dsselection == "")
						dsselection = dsselection + "Cnum=?";
					else
						dsselection = dsselection + " and Cnum=?";
					dsselArgs.add(dcoursenum);
				}
				if (dscore != null) {
					if (dsselection == "")
						dsselection = dsselection + "Score=?";
					else
						dsselection = dsselection + " and Score=?";
					dsselArgs.add(dscore);
				}
				
				
				selection = dsselection;
				selectionArgs = dsselArgs.toArray(new String[dsselArgs.size()]);
				
				deleteScore("choose",selection,selectionArgs);
				Toast.makeText(ScoreDelete.this, "É¾³ý³É¹¦£¡", Toast.LENGTH_LONG).show();
			}
		});
		dscoreCancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent_dscore=new Intent();
				intent_dscore.setClass(ScoreDelete.this, MainActivity.class);
				startActivity(intent_dscore);
				ScoreDelete.this.finish();
			}
		});
	}
	
	private void deleteScore(String table, String whereClause, String[] whereArgs) {
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
