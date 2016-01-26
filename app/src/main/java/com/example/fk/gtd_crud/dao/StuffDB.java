package com.example.fk.gtd_crud.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StuffDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "stuff";
    public static final String DB_NAME = "gtd.db";
    public static final int DB_VERSION = 1;

    public StuffDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ");
        sql.append(TABLE_NAME);
        sql.append("(");
        sql.append(StuffDAO.ID);
        sql.append(" integer primary key autoincrement, ");
        sql.append(StuffDAO.ISDONE);
        sql.append(" integer, ");
        sql.append(StuffDAO.TITLE);
        sql.append(" text, ");
        sql.append(StuffDAO.DESCRIPTION);
        sql.append(" text, ");
        sql.append(StuffDAO.CONTACT);
        sql.append(" text, ");
        sql.append(StuffDAO.CONTEXT);
        sql.append(" text, ");
        sql.append(StuffDAO.LOCATION);
        sql.append(" text");
        sql.append(")");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // recreate db
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
