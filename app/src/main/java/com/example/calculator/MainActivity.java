package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
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
            //当按下清零号时的情况
            case R.id.AC:
                et_input.setText("0");
                //System.out.println(et_input.getText());
                break;
            //当按下数字和小数点时所响应的情况
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
                //若当前输入数为0则设置文本编辑框为””
                if (str.equals("0")) {
                    et_input.setText(strButton);
                    //System.out.println(et_input.getText());
                } else
                    et_input.setText(str + strButton);
                    //System.out.println(et_input.getText());
                break;
            case R.id.radix_point:
                //若当前输入数为0则设置文本编辑框为””
                if (str.equals("0")) {
                    et_input.setText("0"+strButton);
                    //System.out.println(et_input.getText());
                } else
                    et_input.setText(str + strButton);
                //System.out.println(et_input.getText());
                break;
            case R.id.plus_sign:
            case R.id.minus_sign:
            case R.id.multiply_sign:
            case R.id.divide_sign:
                et_input.setText(str + strButton);
                //System.out.println(et_input.getText());
                break;
            case R.id.positiveandnegative:
                Double data = Double.parseDouble(et_input.getText().toString());
                if(et_input.getText()!=null) {
                    data = -data;
                    str = String.valueOf(data);
                    et_input.setText(str);
                    break;
                }
            case R.id.percent_sign:
                Double data1 = Double.parseDouble(et_input.getText().toString());
                if(et_input.getText()!=null) {
                    data1 = 0.01*data1;
                    str = String.valueOf(data1);
                    et_input.setText(str);
                    break;
                }
            case R.id.equal_sign:
                MyCalc obj = new MyCalc(str);
                double ret = obj.Calc();
                int Ret = (int) ret;
                if (ret == Ret) {
                    et_input.setText(String.valueOf(Ret));
                   // System.out.println(et_input.getText());
                }
                else
            {
                et_input.setText(String.valueOf(ret));
                //System.out.println(et_input.getText());
            }
                break;
        }
    }

    class MyCalc {
        private String input;

        public MyCalc(String input) {
            this.input = input;
            // 预处理
            this.input = (input).replaceAll(" ", "");// 去空格
            if ('-' == input.charAt(0)) {// 开头为负数，如-1，改为0-1
                this.input = 0 + this.input;
            }
        }
        public double Calc() {
            if (TextUtils.isEmpty(input))
                return 0;
            Pattern pattern = Pattern.compile("[+×÷-]"); //pattern.compile提取一段字符串中特定范围内的内容
            Pattern pattern1 = null;
            //Pattern pattern2 = null;
            String[] nums = pattern.split(input);//将+、-、×、÷、%断开分割成字符串对象数组，留下数字
            if(nums.length==2) {
                pattern1 = Pattern.compile("\\d*");
                //pattern1 = Pattern.compile(nums[1]);//pattern.compile提取一段字符串中特定范围内的内容
            }
            else if(nums.length==3) {
                double third = Double.parseDouble(nums[2]);
                pattern1 = Pattern.compile("\\d*");
            }

            String[] fuhao = pattern1.split(input);
            for(String s: fuhao){
                System.out.println(fuhao);
            }
            ArrayList<String> fuhao1 = new ArrayList<String>();
            for(int i=0;i<fuhao.length;i++)
            {
               if("".equals(fuhao[i])){
                   continue;
               }
                else if(".".equals(fuhao[i])){
                    continue;
                }
                else {
                   fuhao1.add(fuhao[i]);
               }
            }
            Matcher matcher = pattern.matcher(input);//返回matcher对象,
            if (matcher.find() == false)//调用matcher.find判断input里是否含有+、-、×、÷如果没有+、-、×、÷，返回
                return 0;
            String op = matcher.group();//返回匹配得到的字符串。
            double first = Double.parseDouble(nums[0]);
            double second = Double.parseDouble(nums[1]);
            double sum = 0;
            BigDecimal b1 = new BigDecimal(sum);
            try {
                switch (op) {
                    case "+":
                        sum = first + second;
                        break;
                    case "-":
                        if(nums.length==3){
                            double third = Double.parseDouble(nums[2]);
                            if(fuhao1.get(1).equals("+"))
                                sum = first - second+third;
                            else if(fuhao1.get(1).equals("-"))
                                sum = first - second-third;
                            else if(fuhao1.get(1).equals("×"))
                                sum = (first - second)*third;
                            else if(fuhao1.get(1).equals("÷"))
                                sum = (first - second)/third;
                        }
                        else
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