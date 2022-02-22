package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button plusBtn;
    private Button powBtn;
    private Button minusBtn;
    private Button divBtn;
    private Button mulBtn;
    private Button modBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusBtn = findViewById(R.id.plus);
        powBtn = findViewById(R.id.power);
        minusBtn = findViewById(R.id.minus);
        divBtn = findViewById(R.id.divide);
        mulBtn = findViewById(R.id.multiply);
        modBtn = findViewById(R.id.mod);


        plusBtn.setOnClickListener(this);
        powBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        divBtn.setOnClickListener(this);
        mulBtn.setOnClickListener(this);
        modBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText e1 = (EditText)findViewById(R.id.plain_text_input1);
        EditText e2 = (EditText)findViewById(R.id.plain_text_input2);
        TextView res = (TextView)findViewById(R.id.result);
        String s11=e1.getText().toString();
        String s22=e2.getText().toString();
        switch (view.getId()) {
            case R.id.plus:
                doSum(view, s11, s22, res);
                break;
            case R.id.power:
                doPow(view, s11, s22, res);
                break;
            case R.id.minus:
                doMinus(view, s11, s22, res);
                break;
            case R.id.divide:
                doDiv(view, s11, s22, res);
                break;
            case R.id.multiply:
                doMul(view, s11, s22, res);
                break;
            case R.id.mod:
                doMod(view, s11, s22, res);
                break;
            default:
                break;
        }
    }

    private void doSum(View v, String s11, String s22, TextView res) {
        try {
            int n11 = Integer.parseInt(s11);
            int n22 = Integer.parseInt(s22);
            int final_sum = n11 + n22;
            res.setText(String.valueOf(final_sum));
        }catch (NumberFormatException e){
            res.setText(e.toString());
        }
    }

    private void doPow(View v, String s11, String s22, TextView res) {
        try {
            int n11 = Integer.parseInt(s11);
            int n22 = Integer.parseInt(s22);
            int final_pow = new Double(Math.pow(n11,n22)).intValue();
            res.setText(String.valueOf(final_pow));
        }catch (NumberFormatException e){
            res.setText(e.toString());
        }
    }

    private void doMinus(View v, String s11, String s22, TextView res) {
        try {
            int n11 = Integer.parseInt(s11);
            int n22 = Integer.parseInt(s22);
            int final_minus = n11-n22;
            res.setText(String.valueOf(final_minus));
        }catch (NumberFormatException e){
            res.setText(e.toString());
        }
    }

    private void doDiv(View v, String s11, String s22, TextView res) {
        try {
            int n11 = Integer.parseInt(s11);
            int n22 = Integer.parseInt(s22);
            try{
                int final_div = n11/n22;
                res.setText(String.valueOf(final_div));
            }catch (ArithmeticException e){
                res.setText(e.toString());
            }
        }catch (NumberFormatException e){
            res.setText(e.toString());
        }
    }

    private void doMul(View v, String s11, String s22, TextView res) {
        try {
            int n11 = Integer.parseInt(s11);
            int n22 = Integer.parseInt(s22);
            int final_mul = n11*n22;
            res.setText(String.valueOf(final_mul));
        }catch (NumberFormatException e){
            res.setText(e.toString());
        }
    }

    private void doMod(View v, String s11, String s22, TextView res) {
        try {
            int n11 = Integer.parseInt(s11);
            int n22 = Integer.parseInt(s22);
            try{
                int final_mod = n11%n22;
                res.setText(String.valueOf(final_mod));
            }catch (ArithmeticException e){
                res.setText(e.toString());
            }
        }catch (NumberFormatException e){
            res.setText(e.toString());
        }
    }




}