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

public class ShowListb extends Activity {
	DatabaseHelper dbHelper;
	SQLiteDatabase db;
	private SimpleAdapter qb_adapter;
	private ListView qblist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_qblist);
		dbHelper = new DatabaseHelper(this, "system", 1);
		Queryb qbreceive = (Queryb) getIntent().getSerializableExtra(BaseQuery.QB_KEY);
		Cursor c = queryStudent("student", qbreceive.columns, qbreceive.selection, qbreceive.selectionArgs, 
				null, null,null, null);
		showStudent(c);
	    db.close();		
	}

	public Cursor queryStudent(String table, String[] columns, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy, String limit) {
		db = dbHelper.getReadableDatabase();
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}

	private void showStudent(Cursor c) {
		List<HashMap<String, Object>> qbdata = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> itemb;
		String num0, name0, sex0, age0, phone0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			itemb = new HashMap<String, Object>();

			if (c.getColumnIndex("Snum") == -1)
				itemb.put("Snum", null);
			else {
				num0 = c.getString(c.getColumnIndex("Snum"));
				itemb.put("Snum", num0);
			}
			if (c.getColumnIndex("Sname") == -1)
				itemb.put("Sname", null);
			else {
				name0 = c.getString(c.getColumnIndex("Sname"));
				itemb.put("Sname", name0);
			}
			if (c.getColumnIndex("Ssex") == -1)
				itemb.put("Ssex", null);
			else {
				sex0 = c.getString(c.getColumnIndex("Ssex"));
				itemb.put("Ssex", sex0);
			}
			if (c.getColumnIndex("Sage") == -1)
				itemb.put("Sage", null);
			else {
				age0 = c.getString(c.getColumnIndex("Sage"));
				itemb.put("Sage", age0);
			}
			if (c.getColumnIndex("Sphone") == -1)
				itemb.put("Sphnoe", null);
			else {
				phone0 = c.getString(c.getColumnIndex("Sphone"));
				itemb.put("Sphone", phone0);
			}

			qbdata.add(itemb);
		}
		
		qb_adapter = new SimpleAdapter(this, qbdata, R.layout.show_qbitem,
				new String[] { "Snum", "Sname", "Ssex", "Sage", "Sphone" },
				new int[] { R.id.qblnum, R.id.qblname, R.id.qblsex, R.id.qblage, R.id.qblphone });
		
		//View view = LayoutInflater.from(this).inflate(R.layout.show_listb, null);
		qblist = (ListView)findViewById(R.id.qblistview);
		qblist.setAdapter(qb_adapter);
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
