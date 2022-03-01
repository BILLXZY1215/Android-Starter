package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AcActivity extends AppCompatActivity implements View.OnClickListener{

    private Button pressBtn;
    public static final int AGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        pressBtn = findViewById(R.id.press);
        pressBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText name = (EditText)findViewById(R.id.plain_text_input3);
        String name_str= name.getText().toString();
        switch (view.getId()) {
            case R.id.press:
                toAc2(name_str);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AGE_REQUEST) {
            if (resultCode == RESULT_OK){
                Bundle b = data.getExtras();
                TextView age = (TextView)findViewById(R.id.Age_Value);
                if(b != null){
                    age.setText(b.getString("age"));
                }else{
                    age.setText("Not Set!");
                }
            }else if (resultCode == RESULT_CANCELED){
                //TODO
            }

        }
    }

    private void toAc2(String name_str){
        Intent intent = new Intent();
        intent.setClass(AcActivity.this, Ac2Activity.class); // From Activity -> Activity2
        Bundle b = new Bundle();
        b.putString("name", name_str); //Your Name
        TextView age = (TextView)findViewById(R.id.Age_Value);
        String a = age.getText().toString();
        if(a != ""){
            b.putString("age", a);
        }
        intent.putExtras(b); //Put your Name to your next Intent
        startActivityForResult(intent, AGE_REQUEST);
//        AcActivity.this.startActivity(intent);
//        this.finish();
    }
}