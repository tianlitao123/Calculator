package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] ids = {R.id.Number_0, R.id.Number_1, R.id.Number_2, R.id.Number_3, R.id.Number_4, R.id.Number_5, R.id.Number_6,
                R.id.Number_7, R.id.Number_8, R.id.Number_9, R.id.radix_point, R.id.AC, R.id.positiveandnegative, R.id.percent_sign,
                R.id.divide_sign, R.id.multiply_sign, R.id.minus_sign, R.id.plus_sign, R.id.equal_sign};//按钮数组
        for (int i = 0; i < ids.length; i++)
            findViewById(ids[i]).setOnClickListener(this);//给每个按钮设置一个监听事件
    }

    @Override
    public void onClick(View view) {
        EditText et_input = (EditText) findViewById(R.id.printf);
        Button btn = (Button) view;
        String str = et_input.getText().toString();//XML输入
        String strButton = btn.getText().toString();//按钮按下获得的文本
        switch (view.getId()) {
            case R.id.Number_0:
            case R.id.Number_1:
            case R.id.Number_2:
            case R.id.Number_3:
            case R.id.Number_4:
            case R.id.Number_5:
            case R.id.Number_6:
            case R.id.Number_7:
            case R.id.Number_8:
            case R.id.Number_9:
                if (str.equals("0")) {
                    et_input.setText(strButton);
                } else
                    et_input.setText(str + strButton);
                break;
            case R.id.AC:
                et_input.setText("0");
                break;
            case R.id.plus_sign:
            case R.id.minus_sign:
            case R.id.multiply_sign:
            case R.id.divide_sign:
                et_input.setText(str + strButton);
                break;
            case R.id.equal_sign:
                MyCalc obj = new MyCalc(str);
                double ret = obj.Calc();
                int Ret = (int) ret;
                if (ret == Ret)
                    et_input.setText(String.valueOf(Ret));
                else
                    et_input.setText(String.valueOf(ret));
                break;
        }
    }

    class MyCalc {
        private String input;

        public MyCalc(String input) {
            this.input = input;
        }

        public double Calc() {
            if (TextUtils.isEmpty(input))
                return 0;
            Pattern pattern = Pattern.compile("[+(×)(÷)/-]");
            String[] nums = pattern.split(input);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find() == false)
                return 0;
            String op = matcher.group(0);
            double first = Double.parseDouble(nums[0]);
            double second = Double.parseDouble(nums[1]);
            double sum = 0;
            try {
                switch (op) {
                    case "+":
                        sum = first + second;
                        break;
                    case "-":
                        sum = first - second;
                        break;
                    case "×":
                        sum = first * second;
                        break;
                    case "÷":
                        sum = first / second;
                        break;
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "出错", Toast.LENGTH_LONG).show();
            }
            return sum;
        }
    }
}