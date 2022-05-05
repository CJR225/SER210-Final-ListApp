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
    private String[] allColumns = {ListDatabaseHelper.COLUMN_ID, ListDatabaseHelper.COLUMN_NAME};

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

        public Reminders createList(String name) {
            ContentValues values = new ContentValues();
            values.put(ListDatabaseHelper.COLUMN_NAME, name);

            long insertId = database.insert(ListDatabaseHelper.TABLE_LISTS, null,
                    values);


            Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,
                    allColumns, ListDatabaseHelper.COLUMN_ID + " = " + insertId, null,
                    null, null, null);

            cursor.moveToFirst();

            Reminders newList = cursorToList(cursor);
            cursor.close();

            return newList ;
        }

        public Reminders addItem(String item) {

            return null;
        }

        public void deleteList (Reminders list) {
            long id = list.getId();
            System.out.println("List deleted with id: " + id);
            database.delete(ListDatabaseHelper.TABLE_LISTS, ListDatabaseHelper.COLUMN_ID
                    + " = " + id, null);
        }

        public List<Reminders> getAllLists() {
            List<Reminders> lists = new ArrayList<Reminders>();

            Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Reminders list = cursorToList(cursor);
                lists.add(list);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return lists;
        }

        private Reminders cursorToList(Cursor cursor) {
            Reminders comment = new Reminders();
            comment.setId(cursor.getLong(0));
            comment.setListName(cursor.getString(1));
            return comment;
        }
    }
/*
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

    public Lists addItem (Lists list, String item) {
        //grab specific ID
        long insertId = list.getId();
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allNameColumns,ListDatabaseHelper.LIST_ID + " = " + insertId, null,null,null,null);
        Lists newLists = cursorToList(cursor);
        newLists.setItems(item);
        cursor.close();

        return newLists;
    }

    public Lists removeItem (Lists list, String item) {
        //grab specific ID
        long insertId = list.getId();
        Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,allNameColumns,ListDatabaseHelper.LIST_ID + " = " + insertId, null,null,null,null);
        Lists newLists = cursorToList(cursor);
        newLists.delete(item);
        cursor.close();
        return newLists;
    }

    public void deleteList(Lists list) {
        long id = list.getId();
        database.delete(ListDatabaseHelper.TABLE_LISTS, ListDatabaseHelper.LIST_ID + " = " + id, null );

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

    private Lists cursorToList(Cursor cursor) {
        Lists list = new Lists();
        list.setId(cursor.getLong(0));
        list.setListName(cursor.getString(1));
        return list;
    }

 */

