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

public class ScoreRegedit extends Activity {
	DatabaseHelper dbHelper;
	SQLiteDatabase db;
	private EditText scnumIn,coursenumIn,scoreIn;
	private Button scoreConfirm,scoreCancel;
	private String scnum,coursenum,score;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_regedit);
		dbHelper=new DatabaseHelper(this,"system",1);
	    scnumIn=(EditText)findViewById(R.id.scnumin);
		coursenumIn=(EditText)findViewById(R.id.coursenumin);
		scoreIn=(EditText)findViewById(R.id.scorein);
		score=scoreIn.getText().toString();
		scoreConfirm=(Button)findViewById(R.id.score_confirm);
		scoreCancel=(Button)findViewById(R.id.score_cancel);
		/*insertChoose("33001", "C002", 53);
		insertChoose("33001", "C004", 89);
		insertChoose("33002", "C005", 65);
		insertChoose("33004", "C004", 75);
		insertChoose("33004", "C001", 85);
		insertChoose("33005", "C001", 92);
		insertChoose("33005", "C003", 76);*/
		
		scnumIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				scnum=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		coursenumIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				coursenum=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		scoreIn.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				score=s.toString();		
			}
			@Override
			public void afterTextChanged(Editable s) {
			}	
		});
		scoreConfirm.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(scnum==null){
					Toast.makeText(ScoreRegedit.this, "学号不能为空",Toast.LENGTH_SHORT).show();
				    return;
				}
				if(coursenum==null){
					Toast.makeText(ScoreRegedit.this, "课程号不能为空",Toast.LENGTH_SHORT).show();
				    return;
				}
				if(score==null){
					Toast.makeText(ScoreRegedit.this, "成绩不能为空",Toast.LENGTH_SHORT).show();
				    return;
				}
				int a = dbHelper.getReadableDatabase().rawQuery("select *from choose where Snum=? and Cnum=?", new String[] { scnum,coursenum }).getCount();
				if(a==0){
					insertChoose(scnum,coursenum,Integer.parseInt(score));
					Toast.makeText(ScoreRegedit.this, "插入成功！", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(ScoreRegedit.this, "已有相同选课信息！", Toast.LENGTH_LONG).show();
					return;
				}
				
			}
		});
		scoreCancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent_course=new Intent(ScoreRegedit.this, MainActivity.class);
				startActivity(intent_course);
				ScoreRegedit.this.finish();
			}
		});
	}
	
	private void insertChoose(String Snum, String Cnum, int Score) {
		db = dbHelper.getReadableDatabase();
		db.execSQL("insert into choose values(?,?,?)", new Object[]{Snum,Cnum,Score});
		db.close();
	}
}
