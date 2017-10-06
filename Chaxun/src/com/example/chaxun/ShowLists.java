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

public class ShowLists extends Activity {
	DatabaseHelper dbHelper;
	SQLiteDatabase db ;
	private SimpleAdapter qs_adapter;
	private ListView qslist;
	private Button qscoreshowCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_qslist);
		dbHelper = new DatabaseHelper(this, "system", 1);
		/*qscoreshowCancel = (Button) findViewById(R.id.qsbutton);
		qscoreshowCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_qscoreshow = new Intent();
				intent_qscoreshow.setClass(ShowLists.this, ScoreQuery.class);
				startActivity(intent_qscoreshow);
				ShowLists.this.finish();

			}
		});*/
		Querys qsreceive = (Querys) getIntent().getSerializableExtra(ScoreQuery.QS_KEY);
		Cursor c = queryScore("choose", qsreceive.columns, qsreceive.selection, qsreceive.selectionArgs, null, null,
				null, null);

		showScore(c);
		db.close();

	}

	private Cursor queryScore(String table, String[] columns, String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		db = dbHelper.getReadableDatabase();
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}

	private void showScore(Cursor c) {
		List<HashMap<String, Object>> qsdata = new ArrayList<HashMap<String, Object>>();
		String snum0, cnum0, score0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			if (c.getColumnIndex("Snum") == -1)
				item.put("Snum", null);
			else {
				snum0 = c.getString(c.getColumnIndex("Snum"));
				item.put("Snum", snum0);
			}

			if (c.getColumnIndex("Cnum") == -1)
				item.put("Cnum", null);
			else {
				cnum0 = c.getString(c.getColumnIndex("Cnum"));
				item.put("Cnum", cnum0);
			}
			
			if (c.getColumnIndex("Score") == -1)
				item.put("Score", null);
			else {
				score0 = c.getString(c.getColumnIndex("Score"));
				item.put("Score", score0);
			}
			qsdata.add(item);
		}

		qs_adapter = new SimpleAdapter(this, qsdata, R.layout.show_qsitem,
				new String[] { "Snum","Cnum",  "Score"},
				new int[] { R.id.qlscnum, R.id.qlcoursenum, R.id.qlscore});
		
		//View view = LayoutInflater.from(this).inflate(R.layout.show_listb, null);
		qslist = (ListView)findViewById(R.id.qslistview);
		qslist.setAdapter(qs_adapter);
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
