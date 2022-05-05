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
            newList.setListName(name);
            cursor.close();

            return newList ;
        }

        public Reminders addItem(String item) {

            ContentValues values = new ContentValues();
            values.put(ListDatabaseHelper.COLUMN_ITEM1, item);

            Cursor cursor = database.query(ListDatabaseHelper.TABLE_LISTS,
                    allColumns, ListDatabaseHelper.COLUMN_ITEM1 + " = " + item, null,
                    null, null, null);

            cursor.moveToFirst();

            Reminders newList = cursorToList(cursor);
            newList.setItem1(item);
            cursor.close();

            return newList;
        }

        public void renameList (Reminders list, String name) {
            list.setListName(name);

            long id = list.getId();
            database.replace(ListDatabaseHelper.TABLE_LISTS, ListDatabaseHelper.COLUMN_NAME
                    + " = " + name, null);
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
            Reminders list = new Reminders();
            list.setId(cursor.getLong(0));
            list.setListName(cursor.getString(1));
            //if(cursor.getString(2) != null) {
            //    list.setItem1(cursor.getString(2));
            //}
            return list;
        }
    }