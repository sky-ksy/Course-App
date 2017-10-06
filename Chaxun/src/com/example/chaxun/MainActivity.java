package com.example.chaxun;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;

public class MainActivity extends Activity {
	DatabaseHelper dbHelper;
	private Spinner regedit0,query0, update0,delete0;
	private ArrayAdapter<String> adapterlu;
	private ArrayAdapter<String> adaptercha;
	private ArrayAdapter<String> adaptergeng;
	private ArrayAdapter<String> adaptershan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		regedit0 = (Spinner) findViewById(R.id.regedit);
		query0 = (Spinner) findViewById(R.id.query);
		update0 = (Spinner) findViewById(R.id.update);
		delete0=(Spinner) findViewById(R.id.delete);
		
		String[] sortRegedit = { "录入信息", "基本信息", "课程信息", "选课信息" };
		ArrayList<String> lu = new ArrayList<String>();
		for (int i = 0; i < sortRegedit.length; i++) {
			lu.add(sortRegedit[i]);
		}
		adapterlu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lu);
		adapterlu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		regedit0.setAdapter(adapterlu);
		regedit0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String sort = adapterlu.getItem(arg2);
				Intent intent_main_regedit;
				switch (sort) {
				case "基本信息":
					intent_main_regedit = new Intent(MainActivity.this, BaseRegedit.class);
					startActivity(intent_main_regedit);
					//MainActivity.this.finish();
					break;
				case "课程信息":
					intent_main_regedit = new Intent(MainActivity.this, CourseRegedit.class);
					startActivity(intent_main_regedit);
					//MainActivity.this.finish();
					break;
				case "选课信息":
					intent_main_regedit = new Intent(MainActivity.this, ScoreRegedit.class);
					startActivity(intent_main_regedit);
					//MainActivity.this.finish();
					break;
				default:
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		String[] sortQuery = { "查询信息", "基本信息", "课程信息", "选课信息" };
		ArrayList<String> cha = new ArrayList<String>();
		for (int i = 0; i < sortQuery.length; i++) {
			cha.add(sortQuery[i]);
		}
		adaptercha= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cha);
		adaptercha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		query0.setAdapter(adaptercha);
		query0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String sort1 = adaptercha.getItem(arg2);
				Intent intent_main_query;
				switch (sort1) {
				case "基本信息":
					intent_main_query = new Intent(MainActivity.this, BaseQuery.class);
					startActivity(intent_main_query);
					//MainActivity.this.finish();
					break;
				case "课程信息":
					intent_main_query = new Intent(MainActivity.this, CourseQuery.class);
					startActivity(intent_main_query);
					//MainActivity.this.finish();
					break;
				case "选课信息":
					intent_main_query = new Intent(MainActivity.this, ScoreQuery.class);
					startActivity(intent_main_query);
					//MainActivity.this.finish();
					break;
				default:
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		String[] sortUpdate = { "更新信息", "基本信息", "课程信息", "选课信息" };
		ArrayList<String> geng = new ArrayList<String>();
		for (int i = 0; i < sortUpdate.length; i++) {
			geng.add(sortUpdate[i]);
		}
		adaptergeng= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, geng);
		adaptergeng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		update0.setAdapter(adaptergeng);
		update0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String sort2 = adaptergeng.getItem(arg2);
				Intent intent_main_update;
				switch (sort2) {
				case "基本信息":
					intent_main_update = new Intent(MainActivity.this, BaseUpdate.class);
					startActivity(intent_main_update);
					//MainActivity.this.finish();
					break;
				case "课程信息":
					intent_main_update = new Intent(MainActivity.this, CourseUpdate.class);
					startActivity(intent_main_update);
					//MainActivity.this.finish();
					break;
				case "选课信息":
					intent_main_update= new Intent(MainActivity.this, ScoreUpdate.class);
					startActivity(intent_main_update);
					//MainActivity.this.finish();
					break;
				default:
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		String[] sortDelete = { "删除信息", "基本信息", "课程信息", "选课信息" };
		ArrayList<String> shan = new ArrayList<String>();
		for (int i = 0; i < sortDelete.length; i++) {
			shan.add(sortDelete[i]);
		}
		adaptershan= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shan);
		adaptershan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		delete0.setAdapter(adaptershan);
		delete0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String sort3 = adaptershan.getItem(arg2);
				Intent intent_main_delete;
				switch (sort3) {
				case "基本信息":
					intent_main_delete = new Intent(MainActivity.this, BaseDelete.class);
					startActivity(intent_main_delete);
					//MainActivity.this.finish();
					break;
				case "课程信息":
					intent_main_delete = new Intent(MainActivity.this, CourseDelete.class);
					startActivity(intent_main_delete);
					//MainActivity.this.finish();
					break;
				case "选课信息":
					intent_main_delete= new Intent(MainActivity.this, ScoreDelete.class);
					startActivity(intent_main_delete);
					//MainActivity.this.finish();
					break;
				default:
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {

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
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */

}
