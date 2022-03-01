package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ac2Activity extends AppCompatActivity implements View.OnClickListener{

    private Button pressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac2_main);
        pressBtn = findViewById(R.id.press);
        pressBtn.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        TextView name = (TextView)findViewById(R.id.Name_Value);
        if(b != null){
            name.setText(b.getString("name"));

            EditText age = (EditText) findViewById(R.id.plain_text_input4);
            age.setText(b.getString("age"));
        }else{
            name.setText("Not Set!");
        }
    }

    @Override
    public void onClick(View view) {
        try {
            EditText age = (EditText) findViewById(R.id.plain_text_input4);
            String age_str= age.getText().toString();
            switch (view.getId()) {
                case R.id.press:
                    toAc(age_str);
                    break;
                default:
                    break;
            }
        } catch (Exception e){
            pressBtn.setText(e.toString());
        }
    }

    private void toAc(String age_str){
        Intent intent = new Intent();
        intent.setClass(Ac2Activity.this, AcActivity.class); // From Activity2 -> Activity
        Bundle b = new Bundle();
        b.putString("age", age_str); //Your age
        intent.putExtras(b); //Put your age to your next Intent
        setResult(AcActivity.RESULT_OK, intent);
//        Ac2Activity.this.startActivity(intent);
        this.finish();
    }
}