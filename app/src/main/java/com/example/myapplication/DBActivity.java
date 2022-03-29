package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class DBActivity extends AppCompatActivity implements View.OnClickListener{
    private Button updateDBBtn;
    private Button readDBBtn;
    DBHelper mDbHelper;
//    SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);
//        setContentView(LayoutInflater.from(this).inflate(R.layout.db, null, false));
        updateDBBtn = findViewById(R.id.updateDB);
        readDBBtn = findViewById(R.id.readDB);
        updateDBBtn.setOnClickListener(this);
        readDBBtn.setOnClickListener(this);

        mDbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateDB:
                updateDB(view);
                break;
            case R.id.readDB:
                readDB(view);
                break;
            default:
                break;
        }
    }

    public void updateDB(View v){
        EditText fruitName = (EditText)findViewById(R.id.fruitName);
        EditText fruitColor = (EditText)findViewById(R.id.fruitColor);
        String fN = fruitName.getText().toString();
        String fC = fruitColor.getText().toString();
//        mDb.execSQL("INSERT INTO FruitList (name, color) " + "VALUES " +"('" + fN + "','" + fC + "');");
        mDbHelper.updateFruit(fN, fC);
    }

    public void readDB(View v){
        EditText displayFruit = (EditText)findViewById(R.id.displayFruit);
        String res = mDbHelper.readFruit();
        displayFruit.setText(res);

        ListView listView = (ListView)findViewById(R.id.dbListView);
        SimpleCursorAdapter mDataAdapter = mDbHelper.mapping(this);
        listView.setAdapter(mDataAdapter);
    }
}
