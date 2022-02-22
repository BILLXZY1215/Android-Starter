package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button toCalculator;
    private Button toLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toCalculator = findViewById(R.id.toCalculator);
        toLayout = findViewById(R.id.toLayout);
        toCalculator.setOnClickListener(this);
        toLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toCalculator:
                toCalc(view);
                break;
            case R.id.toLayout:
                toLay(view);
                break;
            default:
                break;
        }
    }

    private void toCalc(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CalculatorActivity.class); // From Main -> Calculator
        MainActivity.this.startActivity(intent);
    }

    private void toLay(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LayoutActivity.class); // From Main -> Calculator
        MainActivity.this.startActivity(intent);
    }

}