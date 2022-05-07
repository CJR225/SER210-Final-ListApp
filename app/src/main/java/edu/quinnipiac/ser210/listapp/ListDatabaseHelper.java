package edu.quinnipiac.ser210.listapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//NO ITEM IMPLEMENTATION
public class ListDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "lists.db";
    public static final String TABLE_LISTS = "ListHolder";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "ListNames";
    public static final String COLUMN_ITEM1 = "Item1";


    public static final int VERSION = 5;
    private static final String DATABASE_CREATE = "create table "
            + TABLE_LISTS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public ListDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ListDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);
        onCreate(db);
    }
}
