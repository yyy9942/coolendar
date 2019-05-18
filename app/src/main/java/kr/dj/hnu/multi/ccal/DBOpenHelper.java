package kr.dj.hnu.multi.ccal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {
    // 상수 영역
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "db_event";
    public static final String TABLE_EVENT_NALE = "tb_events";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String DATE = "date";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    /*
    SQL.. CREATE TABLE EVENT
     */
    public static final String  CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT_NALE
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + " TEXT,"
            + CONTENT + " TEXT,"
            + DATE + " TEXT,"
            + MONTH + " TEXT,"
            + YEAR + " TEXT);";


    /*
    SQL.. DROP TABLE EVENT
     */
    public static final String DROP_EVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_EVENT_NALE;

    // 상수 영역 끝

    /**
     *  이벤트를 생성하여 이벤트 테이블에 값을 저장하는 메서드

     */
    public void saveEvent(Event event){
        ContentValues values = new ContentValues();
        values.put(TITLE, event.getTitle());
        values.put(CONTENT, event.getContent());
        values.put(DATE, event.getDate());
        values.put(MONTH,event.getMonth());
        values.put(YEAR, event.getYear());
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_EVENT_NALE, null, values);
    }

    public int getEventNum(Calendar calendar){
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int num_of_event = 0;
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_EVENT_NALE + " WHERE " + DATE + " = '" + date + "' AND " + MONTH + " = '" + month + "' AND " + YEAR + " = '" + year + "'";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            num_of_event++;
        }
        return num_of_event;
    }

    public List<Event> getEvents(Calendar calendar){
        List<Event> eventList = new ArrayList<>();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT " + TITLE + ", " + CONTENT + ", " + DATE + ", " + MONTH + ", " + YEAR
                + " FROM " + TABLE_EVENT_NALE
                + " WHERE " + DATE + " = '" + date + "' AND " + MONTH + " = '" + month + "' AND " + YEAR + " = '" + year + "'";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String db_title = cursor.getString(0);
            String db_content = cursor.getString(1);
            String db_date = cursor.getString(2);
            String db_month = cursor.getString(3);
            String db_year = cursor.getString(4);
            eventList.add(new Event(db_title, db_content, db_date, db_month, db_year));
        }
        return eventList;
    }







    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENT_TABLE);
        onCreate(db);
    }
}
