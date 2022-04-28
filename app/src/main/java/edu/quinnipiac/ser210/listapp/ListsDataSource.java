package edu.quinnipiac.ser210.listapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListsDataSource {
    private SQLiteDatabase database;
    private ListDatabaseHelper dbHelper;
    private String[] allNameColumns = {ListDatabaseHelper.LIST_ID, ListDatabaseHelper.LIST_NAME};
    private String[] allItem1Columns = {ListDatabaseHelper.LIST_ID, ListDatabaseHelper.LIST_ITEM_1};

    public ListsDataSource(Context context) {
        dbHelper = new ListDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void close() throws SQLException {
        dbHelper.close();
    }

    public Lists addListName (String listName) {
        ContentValues values = new ContentValues();
        values.put(ListDatabaseHelper.LIST_NAME,listName);
        long insertId = database.insert(ListDatabaseHelper.TABLE_LISTS,null,values);
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allNameColumns,ListDatabaseHelper.LIST_ID + " = " + insertId, null,null,null,null);
        cursor.moveToFirst();
        Lists newLists = cursorToList(cursor);
        newLists.setListName(listName);
        cursor.close();

        return newLists;
    }

    public Lists addItem1 (String item1) {
        ContentValues item1values = new ContentValues();
        item1values.put(ListDatabaseHelper.LIST_ITEM_1,item1);
        long insertId = database.insert(ListDatabaseHelper.TABLE_LISTS,null,item1values);
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allNameColumns,ListDatabaseHelper.LIST_ID + " = " + insertId, null,null,null,null);
        cursor.moveToFirst();
        Lists newLists = cursorToList(cursor);
        newLists.setItem1(item1);
        cursor.close();

        return newLists;
    }

    /*
    public Lists addItem (String item) {
        ContentValues item1values = new ContentValues();
        item1values.put(ListDatabaseHelper.LISTS,item);
        long insertId = database.insert(ListDatabaseHelper.TABLE_LISTS,null,item1values);
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allNameColumns,ListDatabaseHelper.LIST_ID + " = " + insertId, null,null,null,null);
        cursor.moveToFirst();
        Lists newLists = cursorToList(cursor);
        newLists.setItem1(item);
        cursor.close();

        return newLists;
    }
    */

    public void deleteList(Lists list) {
        long id = list.getId();
        database.delete(ListDatabaseHelper.TABLE_LISTS, ListDatabaseHelper.LIST_ID + " = " + id, null  );

    }

    public List<Lists> getAllLists() {
        List<Lists> lists = new ArrayList<Lists>();
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allNameColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast()) {
            Lists list = cursorToList(cursor);
            lists.add(list);
            cursor.moveToNext();
        }
        cursor.close();
        return lists;
    }

    public List<Lists> getAllItems1() {
        List<Lists> lists = new ArrayList<Lists>();
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allItem1Columns,null,null,null,null,null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast()) {
            Lists list = cursorToList(cursor);
            lists.add(list);
            cursor.moveToNext();
        }
        cursor.close();
        return lists;
    }

    private Lists cursorToList(Cursor cursor) {
        Lists list = new Lists();
        list.setId(cursor.getLong(0));
        list.setListName(cursor.getString(1));
        return list;
    }
}
