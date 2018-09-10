package com.programming_calendar.workthrough;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.programming_calendar.workthrough.ProgHoursContract.*;

import java.util.ArrayList;
import java.util.List;


public class ProgHoursDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyProgrammingHours.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
	private List<ProgHours> allRecords;

    public ProgHoursDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_PROGRAMMING_HOURS_TABLE = "CREATE TABLE " +
                ProgHoursTable.TABLE_NAME + " ( " +
                ProgHoursTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProgHoursTable.COLUMN_DATE + " DATE, " +
                ProgHoursTable.COLUMN_COLOR + " INTEGER, " +
                ProgHoursTable.COLUMN_JOB + " TEXT, " +
                ProgHoursTable.COLUMN_HOURS + " REAL" +
                ")";

        db.execSQL(SQL_CREATE_PROGRAMMING_HOURS_TABLE);
        //fillProgHoursTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + ProgHoursTable.TABLE_NAME);
        onCreate(db);
    }
	
	public boolean saveOrModifyData(ProgHours ph){
		allRecords = getAllProgHours();
		for (int i = 0; i < allRecords.size(); i++){
			if (ph.getDate().equals(allRecords.get(i).getDate()) && 
				ph.getJob().equals(allRecords.get(i).getJob()))
				{
					db.execSQL("UPDATE " + ProgHoursTable.TABLE_NAME +
								" SET " + ProgHoursTable.COLUMN_HOURS +
								"=\"" + ph.getHours() + "\"" +
								" WHERE " + ProgHoursTable.COLUMN_DATE +
								"=\"" + ph.getDate() + "\"" +
								" AND " + ProgHoursTable.COLUMN_JOB +
								"=\"" + ph.getJob() + "\"");
								return true;
				}
		}
		addProgHours(ph);
		return false;
	}
/*
    private void fillProgHoursTable() {
        ProgHours ph1 = new ProgHours("2018-09-09", 0xff7070ff, "Android: kalendarz", 5);
        addProgHours(ph1);
        ProgHours ph2 = new ProgHours("2018-09-10", 0xff7070ff, "Android: kalendarz", 4);
        addProgHours(ph2);
        ProgHours ph3 = new ProgHours("2018-09-10", 0xffffdd66, "Git/GitHub", 3);
        addProgHours(ph3);
        ProgHours ph4 = new ProgHours("2018-09-11", 0xff7070ff, "Android: kalendarz", 4);
        addProgHours(ph4);
        ProgHours ph5 = new ProgHours("2018-09-11", 0xff00a813, "Python", 2);
        addProgHours(ph5);
        ProgHours ph6 = new ProgHours("2018-10-05", 0xff7070ff, "Android: kalendarz", 8);
        addProgHours(ph6);
    }
*/
    private void addProgHours(ProgHours progHours) {
        ContentValues cv = new ContentValues();
        cv.put(ProgHoursTable.COLUMN_DATE, progHours.getDate());
        cv.put(ProgHoursTable.COLUMN_COLOR, progHours.getColor());
        cv.put(ProgHoursTable.COLUMN_JOB, progHours.getJob());
        cv.put(ProgHoursTable.COLUMN_HOURS, progHours.getHours());
        db.insert(ProgHoursTable.TABLE_NAME, null, cv);
    }

    public List<ProgHours> getAllProgHours() {
        List<ProgHours> progHoursList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ProgHoursTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                ProgHours progHours = new ProgHours();
                progHours.setDate(c.getString(c.getColumnIndex(ProgHoursTable.COLUMN_DATE)));
                progHours.setColor(c.getInt(c.getColumnIndex(ProgHoursTable.COLUMN_COLOR)));
                progHours.setJob(c.getString(c.getColumnIndex(ProgHoursTable.COLUMN_JOB)));
                progHours.setHours(c.getInt(c.getColumnIndex(ProgHoursTable.COLUMN_HOURS)));
                progHoursList.add(progHours);
            } while (c.moveToNext());
        }

        c.close();
        return progHoursList;
    }

	public double countProductiveHours (String date){
		double totalProductiveHours = 0;
		db = getReadableDatabase();
        Cursor c = db.rawQuery(
			"SELECT SUM(" + 
			ProgHoursTable.COLUMN_HOURS + 
			") FROM " + ProgHoursTable.TABLE_NAME +
			" WHERE " + ProgHoursTable.COLUMN_DATE +
			"=\"" + date + "\"", 
			null);
		if (c.moveToFirst()) {
            do {
                totalProductiveHours = c.getInt(
					c.getColumnIndex("SUM(" + 
					ProgHoursTable.COLUMN_HOURS + 
					")"));
            } while (c.moveToNext());
        }
		return totalProductiveHours;
	}
}
