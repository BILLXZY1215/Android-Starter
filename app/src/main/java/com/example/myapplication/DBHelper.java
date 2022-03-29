package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class DBHelper extends SQLiteOpenHelper {
    private final String TAG = this.getClass().getName();
    public static final String TABLE_NAME = "FruitList";
    public static final String KEY_ID = "_id";
    public static final String KEY_SUB1 = "name";
    public static final String KEY_SUB2 = "color";
    private static final int DATABASE_VERSION = 1;

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, context.getString(R.string.app_db_name), null, DATABASE_VERSION);
        db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SUB1 + " TEXT," +
                KEY_SUB2 + " TEXT)";
        db.execSQL(query);

        Log.d(TAG, "Table Created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void updateFruit(String fruitName, String fruitColor){
        ContentValues values = new ContentValues();
        values.put(KEY_SUB1, fruitName);
        values.put(KEY_SUB2, fruitColor);
        db.insert(TABLE_NAME, null, values);
        Log.d(TAG, "Fruit Inserted!");
    }

    public String readFruit(){
        String res = "";
        Cursor c = db.query("FruitList", new String[] { KEY_ID, KEY_SUB1, KEY_SUB2 }, null, null, null,
                null, null);
        if(c.moveToFirst()) {
            do{
                int id = c.getInt(0);
                String name = c.getString(1);
                String color = c.getString(2);
                res = res + id + " " + name + " " + color + " \n";
            }while(c.moveToNext());
        }
        return res;
    }

    public SimpleCursorAdapter mapping(Context context){
        Cursor c = db.query("FruitList", new String[] { KEY_ID, KEY_SUB1, KEY_SUB2 }, null, null, null,
                null, null);
        String[] columns = new String[]{ KEY_SUB1,
                KEY_SUB2
        };
        int[] uiMapping = new int[]{
                R.id.DBListNameView, R.id.DBListColorView,
        };

        SimpleCursorAdapter mDataAdapter = new SimpleCursorAdapter( context, R.layout.db_list,
                c,
                columns,
                uiMapping,
                0
        );

        return mDataAdapter;

    }
}
