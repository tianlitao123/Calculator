package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RateActivity  extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

    }

    @Override
    public void onClick(View v) {
        EditText editTextren = (EditText)findViewById(R.id.editTextren);
        EditText editTextmei = (EditText)findViewById(R.id.editTextmei);
        EditText editTexthui = (EditText)findViewById(R.id.editTexthui);
        Intent intent1=new Intent(RateActivity.this,CurrentRateActivity.class);
        double R1 = 0;
        double M;
        double Huan;
        String a;
        String s;
        String b;
        if (v.getId()==R.id.button){
            a=editTextren.getText().toString();
            if("".equals(a)){
                R1 = 0;
            }
            else{

                R1 =Float.valueOf(a);

            }
            b=editTexthui.getText().toString();
            if("".equals(b)){
                Huan = 0;
            }
            else{

                Huan =Float.valueOf(b);

            }
            if (Huan==0){
                Toast.makeText(RateActivity.this,"输入有误",Toast.LENGTH_SHORT).show();
            }
            else {
                M=R1/Huan;
                editTextmei.setText(M+"");
            }

        }
        else if(v.getId()==R.id.button2){
            a=editTextmei.getText().toString();
            if("".equals(a)){
                M = 0;
            }
            else{

                M =Float.valueOf(a);

            }
            b=editTexthui.getText().toString();
            if("".equals(b)){
                Huan = 0;

            }
            else{

                Huan =Float.valueOf(b);

            }
            if (Huan==0){
                Toast.makeText(RateActivity.this,"输入有误",Toast.LENGTH_SHORT).show();
            }else {

                R1=M*Huan;
                editTextren.setText(R1+"");
            }
        }
        else if(v.getId()==R.id.button3) {
            Intent intent = getIntent();
            setResult(1, intent);
            finish();
        }
        else if(v.getId()==R.id.button4) {
            startActivityForResult(intent1,0);
        }
    }
}
