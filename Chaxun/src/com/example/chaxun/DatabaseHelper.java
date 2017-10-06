package com.example.chaxun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	final String CREATE_TABLE_STUDENT = "create table if not exists student("
			+ "Snum Varchar primary key,Sname Varchar,Ssex Varchar,Sage int,Sphone Varchar)";
	final String CREATE_TABLE_COURSE = "create table if not exists course("
			+ "Cnum Varchar primary key,Cname Varchar,Ccredit float)";
	final String CREATE_TABLE_CHOOSE = "create table if not exists choose("
			+ "Snum Varchar ,Cnum Varchar,Score int,primary key(Snum,Cnum))";

	public DatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_STUDENT);
		db.execSQL(CREATE_TABLE_COURSE);
		db.execSQL(CREATE_TABLE_CHOOSE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
}
