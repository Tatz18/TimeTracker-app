package com.arnab.timetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arnab on 7/30/2017.
 */

public class TimeTrackerDatabaseHelper {

    private static final int DATBASE_VERSION = 2;
    private static final String DATBASE_NAME = "timetracker.db";
    private static final String TABLE_NAME = "timerecords";

    public static final String TIMETRACKER_COLUMN_ID = "_id";
    public static final String TIMETRACKER_COLUMN_TIME = "time";
    public static final String TIMETRACKER_COLUMN_NOTES = "notes";

    private TimeTrackerOpenHelper openHelper;
    private SQLiteDatabase database;

    public TimeTrackerDatabaseHelper(Context context) {
        openHelper = new TimeTrackerOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void saveTimeRecord(String time,String notes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIMETRACKER_COLUMN_TIME,time);
        contentValues.put(TIMETRACKER_COLUMN_NOTES,notes);
        database.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor getTimeRecordList() {
        return database.rawQuery("select * from "+TABLE_NAME,null);
    }
    private class TimeTrackerOpenHelper extends SQLiteOpenHelper {
        TimeTrackerOpenHelper(Context context) {
            super(context,DATBASE_NAME,null,DATBASE_VERSION);
        }

        public void onCreate(SQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE "+TABLE_NAME+" ( "
                        +TIMETRACKER_COLUMN_ID+" INTEGER PRIMARY KEY, "
                        +TIMETRACKER_COLUMN_TIME+" TEXT, "
                        +TIMETRACKER_COLUMN_NOTES+" TEXT )"
            );
        }

        public void onUpgrade(SQLiteDatabase database,int oldV,int newV) {
            database.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(database);

        }
    }
}
