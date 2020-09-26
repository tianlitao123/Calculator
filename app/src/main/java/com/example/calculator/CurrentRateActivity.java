package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentRateActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentrate);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.currentButton1) {
            TextView t1 = (TextView) findViewById(R.id.currentRate1);
            t1.setText("美元：人民币=6.8238");
            Toast.makeText(CurrentRateActivity.this, t1.toString(), Toast.LENGTH_SHORT).show();
            TextView t2 = (TextView) findViewById(R.id.currentRate2);
            t2.setText("人民币：日元=15.4793");
            Toast.makeText(CurrentRateActivity.this, t2.toString(), Toast.LENGTH_SHORT).show();
            TextView t3 = (TextView) findViewById(R.id.currentRate3);
            t3.setText("人民币：韩元=172.1372");
            Toast.makeText(CurrentRateActivity.this, t3.toString(), Toast.LENGTH_SHORT).show();
            TextView t4 = (TextView) findViewById(R.id.currentRate4);
            t4.setText("加元：人民币=5.0971");
            Toast.makeText(CurrentRateActivity.this, t4.toString(), Toast.LENGTH_SHORT).show();
            TextView t5 = (TextView) findViewById(R.id.currentRate5);
            t5.setText("人民币：卢布=11.4431");
            Toast.makeText(CurrentRateActivity.this, t5.toString(), Toast.LENGTH_SHORT).show();
            TextView t6 = (TextView) findViewById(R.id.currentRate6);
            t6.setText("欧元：人民币=7.9341");
            Toast.makeText(CurrentRateActivity.this, t6.toString(), Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.currentButton) {
            Intent intent1 = getIntent();
            setResult(0, intent1);
            finish();
        }
    }
}
