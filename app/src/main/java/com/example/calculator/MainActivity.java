package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断屏幕横屏/竖屏
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int ids[] = {R.id.Number_0, R.id.Number_1, R.id.Number_2, R.id.Number_3, R.id.Number_4, R.id.Number_5, R.id.Number_6,
                    R.id.Number_7, R.id.Number_8, R.id.Number_9, R.id.radix_point, R.id.AC, R.id.positiveandnegative, R.id.percent_sign,
                    R.id.divide_sign, R.id.multiply_sign, R.id.minus_sign, R.id.plus_sign, R.id.equal_sign, R.id.left_bracket, R.id.right_bracket,
                    R.id.conversion_rate, R.id.conversion_base, R.id.cal_date, R.id.cal_history,
                    R.id.second_function, R.id.x_square, R.id.x_cube, R.id.conversion_volume, R.id.e_power_x, R.id.ten_x, R.id.one_x, R.id.x_power_one_two,
                    R.id.x_power_one_three, R.id.conversion_length, R.id.ln, R.id.log10, R.id.x_factorial, R.id.sin_function, R.id.cos_function, R.id.tan_function,
                    R.id.e, R.id.EE, R.id.Rad, R.id.sinh, R.id.cosh, R.id.tanh, R.id.π, R.id.Rand};//科学计算器按钮数组
            setContentView(R.layout.scientific_calculator);
            for (int i = 0; i < ids.length; i++)
                findViewById(ids[i]).setOnClickListener(this);//给每个按钮设置一个监听事件
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            int[] ids = {R.id.Number_0, R.id.Number_1, R.id.Number_2, R.id.Number_3, R.id.Number_4, R.id.Number_5, R.id.Number_6,
                    R.id.Number_7, R.id.Number_8, R.id.Number_9, R.id.radix_point, R.id.AC, R.id.positiveandnegative, R.id.percent_sign,
                    R.id.divide_sign, R.id.multiply_sign, R.id.minus_sign, R.id.plus_sign, R.id.equal_sign};//普通计算器按钮数组
            setContentView(R.layout.activity_main);
            for (int i = 0; i < ids.length; i++)
                findViewById(ids[i]).setOnClickListener(this);//给每个按钮设置一个监听事件
        }
        /*int[] ids = {R.id.Number_0, R.id.Number_1, R.id.Number_2, R.id.Number_3, R.id.Number_4, R.id.Number_5, R.id.Number_6,
                R.id.Number_7, R.id.Number_8, R.id.Number_9, R.id.radix_point, R.id.AC, R.id.positiveandnegative, R.id.percent_sign,
                R.id.divide_sign, R.id.multiply_sign, R.id.minus_sign, R.id.plus_sign, R.id.equal_sign,//普通计算器按钮数组
                 R.id.left_bracket,R.id.right_bracket, R.id.memory_clear,R.id.add_to_storage,R.id.minus_to_storage,R.id.memory_read,
                R.id.second_function,R.id.x_square,R.id.x_cube, R.id.x_power_y,R.id.e_power_x,R.id.ten_x,R.id.one_x,R.id.x_power_one_two,
                R.id.x_power_one_three,R.id.x_power_one_y,R.id.ln, R.id.log10,R.id.x_factorial,R.id.sin_function,R.id.cos_function,R.id.tan_function,
                R.id.e,R.id.EE,R.id.Rad,R.id.sinh,R.id.cosh, R.id.tanh,R.id.π,R.id.Rand};//科学计算器按钮数组*/
    }
    //用onCreateOptionsMenu（）显示菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);//getMenuInflater()方法得到MenuInflater
        //调用inflate接收两个参数
        //R.menu.main指调用menu文件下的main资源文件
        return true;//返回true，允许创建的菜单显示,返回false不显示
    }
    //定义菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){   //item.getItemId()判断我们选择那个菜单项
            case R.id.add_help:
                Intent intent=new Intent(MainActivity.this,HelpActivity.class);
                startActivityForResult(intent,0);
                break;
            default:
        }
        return true;
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
                    et_input.setText("0" + strButton);
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
                if (et_input.getText() != null) {
                    data = -data;
                    str = String.valueOf(data);
                    et_input.setText(str);
                    break;
                }
            case R.id.percent_sign:
                Double data1 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data1 = 0.01 * data1;
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
                } else {
                    et_input.setText(String.valueOf(ret));
                    //System.out.println(et_input.getText());
                }
                break;
            /*下面是科学计算器的一些按钮*/
            case R.id.π:
                et_input.setText("3.141592653589793");
                break;
            case R.id.e:
                et_input.setText("2.718281828459045");
                break;
            case R.id.sin_function:
                Double data2 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data2 = Math.sin(data2);
                    str = String.valueOf(data2);
                    et_input.setText(str);
                    break;
                }
            case R.id.cos_function:
                Double data3 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data3 = Math.cos(data3);
                    str = String.valueOf(data3);
                    et_input.setText(str);
                    break;
                }
            case R.id.tan_function:
                Double data4 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data4 = Math.tan(data4);
                    str = String.valueOf(data4);
                    et_input.setText(str);
                    break;
                }
            case R.id.x_square:
                Double data5 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data5 = data5 * data5;
                    str = String.valueOf(data5);
                    et_input.setText(str);
                    break;
                }
            case R.id.x_cube:
                Double data6 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data6 = data6 * data6 * data6;
                    str = String.valueOf(data6);
                    et_input.setText(str);
                    break;
                }
            case R.id.x_factorial:
                Double data7 = Double.parseDouble(et_input.getText().toString());
                Double jiecheng = 1.0;
                if (data7 > 0 && data7 == Math.rint(data7)) {
                    for (int i = 1; i <= data7; i++) {
                        jiecheng *= i;
                    }
                    str = String.valueOf(jiecheng);
                    et_input.setText(str);
                    break;
                }
                if (data7 == 0) {
                    et_input.setText("0");
                    break;
                } else if (data7 < 0 || data7 != Math.rint(data7)) {
                    et_input.setText("输入错误");
                    break;
                }
            case R.id.one_x:
                Double data8 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data8 = 1 / data8;
                    str = String.valueOf(data8);
                    et_input.setText(str);
                    break;
                }
            case R.id.x_power_one_two:
                Double data9 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data9 = Math.sqrt(data9);
                    str = String.valueOf(data9);
                    et_input.setText(str);
                    break;
                }
            case R.id.x_power_one_three:
                Double data10 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data10 = Math.pow(data10, 1.0 / 3.0);
                    str = String.valueOf(data10);
                    et_input.setText(str);
                    break;
                }
            case R.id.e_power_x:
                Double data11 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data11 = Math.pow(Math.E, data11);
                    str = String.valueOf(data11);
                    et_input.setText(str);
                    break;
                }
            case R.id.ten_x:
                Double data12 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data12 = Math.pow(10, data12);
                    str = String.valueOf(data12);
                    et_input.setText(str);
                    break;
                }
            case R.id.ln:
                Double data13 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data13 = Math.log(data13);
                    str = String.valueOf(data13);
                    et_input.setText(str);
                    break;
                }
            case R.id.log10:
                Double data14 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data14 = Math.log10(data14);
                    str = String.valueOf(data14);
                    et_input.setText(str);
                    break;
                }
            case R.id.sinh:
                Double data15 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data15 = Math.sinh(data15);
                    str = String.valueOf(data15);
                    et_input.setText(str);
                    break;
                }
            case R.id.cosh:
                Double data16 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data16 = Math.cosh(data16);
                    str = String.valueOf(data16);
                    et_input.setText(str);
                    break;
                }
            case R.id.tanh:
                Double data17 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    data17 = Math.tanh(data17);
                    str = String.valueOf(data17);
                    et_input.setText(str);
                    break;
                }
            case R.id.left_bracket:
                //若当前输入数为0则设置文本编辑框为””
                if (str.equals("0")) {
                    et_input.setText(strButton + "0");
                } else
                    et_input.setText(str + strButton);
                break;
            case R.id.right_bracket:
                //若当前输入数为0则设置文本编辑框为””
                if (str.equals("0")) {
                    et_input.setText("0" + strButton);
                } else
                    et_input.setText(str + strButton);
                break;
            case R.id.conversion_base:
                Double data20 = Double.parseDouble(et_input.getText().toString());
                JinZhi s1 = new JinZhi();
                int num = Integer.parseInt(str);
                if (et_input.getText() != null) {
                    str = String.valueOf(data20);
                    et_input.setText("十进制为："+str+"二进制为："+s1.fun(2,num)+"八进制为："+s1.fun(8,num)+"十六进制为："+s1.fun(16,num));
                    /*+"八进制为："+JinZhi.fun(8,Integer.parseInt(String.valueOf(data20)))+"十六进制为："+JinZhi.fun(16,Integer.parseInt(String.valueOf(data20)))*/
                    /*JinZhi.fun(2,Integer.parseInt(String.valueOf(data20)));*/
                    break;
                }
            case R.id.conversion_length:
                Double data18 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    str = String.valueOf(data18);
                    String str1 = String.valueOf(data18 * 0.001);
                    String str2 = String.valueOf(data18 * 10);
                    String str3 = String.valueOf(data18 * 100);
                    String str4 = String.valueOf(data18 * 1000);
                    String str5 = String.valueOf(data18 * 1000000);
                    et_input.setText(str + "cm=" + str1 + "km=" + str2 + "dm=" + str3 + "cm=" + str4 + "mm=" + str5 + "um");
                    break;
                }
            case R.id.conversion_volume:
                Double data19 = Double.parseDouble(et_input.getText().toString());
                if (et_input.getText() != null) {
                    str = String.valueOf(data19);
                    String str1 = String.valueOf(data19 * 0.001);
                    String str2 = String.valueOf(data19 * 1000);
                    String str3 = String.valueOf(data19 * 1000000);
                    et_input.setText(str + "dm³=" + str1 + "m³=" + str2 + "cm³=" + str3 + "mm³");
                    break;
                }
            case R.id.conversion_rate:
                Intent intent=new Intent(MainActivity.this,RateActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.cal_date:
                if (et_input.getText() != null) {
                    Date date = new Date();
                    et_input.setText(date.toString());
                    break;
                }
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
            if(input.contains("("))
            {
                // 找出最后一个左括号
                int left = input.lastIndexOf("(");
                // 找出第一个右括号
                int right = input.indexOf(")");
                input = input.substring(left + 1, right);
            }
            String[] nums = pattern.split(input);//将+、-、×、÷、%断开分割成字符串对象数组，留下数字
            if (nums.length == 2) {
                pattern1 = Pattern.compile("\\d*");
                //pattern1 = Pattern.compile(nums[1]);//pattern.compile提取一段字符串中特定范围内的内容
            } else if (nums.length == 3) {
                double third = Double.parseDouble(nums[2]);
                pattern1 = Pattern.compile("\\d*");
            }

            String[] fuhao = pattern1.split(input);
            for (String s : fuhao) {
                System.out.println(fuhao);
            }
            ArrayList<String> fuhao1 = new ArrayList<String>();
            for (int i = 0; i < fuhao.length; i++) {
                if ("".equals(fuhao[i])) {
                    continue;
                } else if (".".equals(fuhao[i])) {
                    continue;
                } else {
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
                        if (nums.length == 3) {
                            double third = Double.parseDouble(nums[2]);
                            if (fuhao1.get(1).equals("+"))
                                sum = first - second + third;
                            else if (fuhao1.get(1).equals("-"))
                                sum = first - second - third;
                            else if (fuhao1.get(1).equals("×"))
                                sum = (first - second) * third;
                            else if (fuhao1.get(1).equals("÷"))
                                sum = (first - second) / third;
                        } else
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

    public  class JinZhi {
        // 十进制转换为 n 进制
        public String fun(int n, int num) {
            // n 表示目标进制, num 要转换的值
            String str = "";
            int yushu;      // 保存余数
            int shang = num;      // 保存商
            while (shang > 0) {
                yushu = shang % n;
                shang = shang / n;

                // 10-15 -> a-f
                if (yushu > 9) {
                    str = (char) ('a' + (yushu - 10)) + str;
                } else {
                    str = yushu + str;
                }
            }

            return str;
        }
    }

}