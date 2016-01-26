package com.example.fk.gtd_crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fk.gtd_crud.R;
import com.example.fk.gtd_crud.model.Stuff;

import java.util.ArrayList;
import java.util.List;

public class StuffDAO {

    public static final String ID = "_id";
    public static final String ISDONE = "isdone";
    public static final String TITLE = "title";
    public static final String DESCRIPTION= "description";
    public static final String CONTACT = "contact";
    public static final String CONTEXT = "context";
    public static final String LOCATION = "location";

    private final StuffDB stuffBD;
    private SQLiteDatabase db;

    public StuffDAO(Context context) {
        stuffBD = new StuffDB(context);
    }

    public ContentValues extractValues(Stuff stuff) {
        ContentValues values = new ContentValues();
        values.put(ISDONE, stuff.getIsDone());
        values.put(TITLE, stuff.getTitle());
        values.put(DESCRIPTION, stuff.getDescription());
        values.put(CONTACT, stuff.getContact());
        values.put(CONTEXT, stuff.getContext());
        values.put(LOCATION, stuff.getLocation());
        return values;
    }

    public int save(Stuff stuff) {
        db = stuffBD.getWritableDatabase();
        long result = db.insert(StuffDB.TABLE_NAME, null, extractValues(stuff));
        db.close();

        if (result == -1) {
            return R.string.db_save_error;
        }

        return R.string.Stuff_saved;
    }

    public int update(Stuff stuff) {
        db = stuffBD.getWritableDatabase();
        long result = db.update(StuffDB.TABLE_NAME, extractValues(stuff), ID + "=" + stuff.getId(), null);
        db.close();

        if (result == -1) {
            return R.string.db_save_error;
        }

        return R.string.Stuff_saved;
    }

    public int delete(Stuff stuff) {
        db = stuffBD.getWritableDatabase();
        long result = db.delete(StuffDB.TABLE_NAME, ID + "=" + stuff.getId(), null);
        db.close();

        if (result == -1) {
            return R.string.delete_error;
        }

        return R.string.stuff_deleted;

    }

    public List<Stuff> getStuffList() {
        List<Stuff> stuffList = new ArrayList<>();
        Cursor cursor;

        db = stuffBD.getReadableDatabase();
        cursor = db.query(StuffDB.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Stuff stuff = new Stuff();
                stuff.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                stuff.setIsDone(cursor.getInt(cursor.getColumnIndex(ISDONE)));
                stuff.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                stuff.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                stuff.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));
                stuff.setContext(cursor.getString(cursor.getColumnIndex(CONTEXT)));
                stuff.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));

                stuffList.add(stuff);
            }
            cursor.close();
        }
        db.close();
        return stuffList;
    }

    public List<Stuff> getStuffListByPage(int page) {
        int limit = 20;
        int offset = page * limit;
        String offsetLimitQuery = Integer.toString(offset) + "," + Integer.toString(limit);

        Log.d("offsetLimitQuery", offsetLimitQuery);

        List<Stuff> stuffList = new ArrayList<>();
        Cursor cursor;


        db = stuffBD.getReadableDatabase();
        cursor = db.query(StuffDB.TABLE_NAME, null, null, null, null, null, null, offsetLimitQuery);

        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Stuff stuff = new Stuff();
                stuff.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                stuff.setIsDone(cursor.getInt(cursor.getColumnIndex(ISDONE)));
                stuff.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                stuff.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                stuff.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));
                stuff.setContext(cursor.getString(cursor.getColumnIndex(CONTEXT)));
                stuff.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));

                stuffList.add(stuff);
            }
            cursor.close();
        }
        db.close();
        Log.d("stuffList.size()", Integer.toString(stuffList.size()));
        return stuffList;
    }

    public List<Stuff> search(String searchQuery) {
        String limit = "20";

        Log.d("searching", searchQuery);

        List<Stuff> stuffList = new ArrayList<>();
        Cursor cursor;

        db = stuffBD.getReadableDatabase();

        String query = " select * from " + StuffDB.TABLE_NAME +
                " where " + StuffDAO.TITLE + " like " + "'%" + searchQuery + "%'"+
                " or " + StuffDAO.DESCRIPTION + " like " + "'%" + searchQuery + "%'"+
                " limit " + limit;

        cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Stuff stuff = new Stuff();
                stuff.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                stuff.setIsDone(cursor.getInt(cursor.getColumnIndex(ISDONE)));
                stuff.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                stuff.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                stuff.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));
                stuff.setContext(cursor.getString(cursor.getColumnIndex(CONTEXT)));
                stuff.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));

                stuffList.add(stuff);
            }
            cursor.close();
        }
        db.close();
        Log.d("stuffList.size()", Integer.toString(stuffList.size()));
        return stuffList;
    }

}
