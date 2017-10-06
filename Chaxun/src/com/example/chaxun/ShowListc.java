package com.example.chaxun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShowListc extends Activity {
	DatabaseHelper dbHelper;
	SQLiteDatabase db ;
	private SimpleAdapter qc_adapter;
	private ListView qclist;
	private Button qcourseshowCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_qclist);
		dbHelper = new DatabaseHelper(this, "system", 1);
		/*qcourseshowCancel = (Button) findViewById(R.id.qcbutton);
		qcourseshowCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_qcourseshow = new Intent();
				intent_qcourseshow.setClass(ShowListc.this, CourseQuery.class);
				startActivity(intent_qcourseshow);
				ShowListc.this.finish();

			}
		});*/
		Queryc qcreceive = (Queryc) getIntent().getSerializableExtra(CourseQuery.QC_KEY);
		Cursor c = queryCourse("course", qcreceive.columns, qcreceive.selection, qcreceive.selectionArgs, null, null,
				null, null);

		showCourse(c);
		db.close();

	}

	private Cursor queryCourse(String table, String[] columns, String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		db = dbHelper.getReadableDatabase();
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}

	private void showCourse(Cursor c) {
		List<HashMap<String, Object>> qcdata = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> itemc;
		String cnum0, cname0, credit0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			itemc = new HashMap<String, Object>();
			if (c.getColumnIndex("Cnum") == -1)
				itemc.put("Cnum", null);
			else {
				cnum0 = c.getString(c.getColumnIndex("Cnum"));
				itemc.put("Cnum", cnum0);
			}
			if (c.getColumnIndex("Cname") == -1)
				itemc.put("Cname", null);
			else {
				cname0 = c.getString(c.getColumnIndex("Cname"));
				itemc.put("Cname", cname0);
			}
			if (c.getColumnIndex("Ccredit") == -1)
				itemc.put("Ccredit", null);
			else {
				credit0 = c.getString(c.getColumnIndex("Ccredit"));
				itemc.put("Ccredit", credit0);
			}
			qcdata.add(itemc);
		}
		qc_adapter = new SimpleAdapter(this, qcdata, R.layout.show_qcitem,
				new String[] { "Cnum", "Cname", "Ccredit"},
				new int[] { R.id.qlcnum, R.id.qlcname, R.id.qlcredit});	
		qclist = (ListView)findViewById(R.id.qclistview);
		qclist.setAdapter(qc_adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_listb, menu);
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
