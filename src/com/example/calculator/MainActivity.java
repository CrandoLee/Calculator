package com.example.calculator;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
	Button btn_0;
	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;
	Button btn_5;
	Button btn_6;
	Button btn_7;
	Button btn_8;
	Button btn_9;
	Button btn_clear;
	Button btn_del;
	Button btn_add;
	Button btn_minus;
	Button btn_multiple;
	Button btn_divide;
	Button btn_point;
	Button btn_equal;
	EditText et_input = null;
	boolean clear_flag = false;			//清空
	
	double x1 = 0,x2 = 0;				//定义两个变量
	
	double first = 0; 					//存储第一个要进行运算的数字
	double second = 0;					//存储第二个要进行运算的数字
	String expression = "";				//存储输入的式子
	String expression_backup = "";		//存储输入式子的备份
	String last_input = "";				//存储上一个输入的字符
	boolean error1 = false;  			//除零错误
	boolean pointflag = false;  		//记录小数点的按下情况
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_del = (Button) findViewById(R.id.btn_del);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_multiple = (Button) findViewById(R.id.btn_multiple);
		btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_point = (Button) findViewById(R.id.btn_point);
		btn_equal = (Button) findViewById(R.id.btn_equal);
		et_input = (EditText) findViewById(R.id.edit_text);
		
		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiple.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
			if (expression.length() == 12) {
				break;
			}
			
			//如果算式第一个字符为0，则自动变为输入的数字
			if (expression.length() == 1 && expression.substring(0,1).equals("0")) {
				expression = ((Button) v).getText() + "";
			}
			else {
				expression = expression + ((Button) v).getText();
			}
			last_input = (String) ((Button) v).getText();
			et_input.setText(expression);
			break;
		case R.id.btn_0:
			if (expression.length() == 12) {
				break;
			}
			
			//禁止在开始的时候输入0003这种类型的数字
			if (expression.length() == 1 && expression.substring(0,1).equals("0")) {
				break;
			}
			
			//如果是上述数字，直接在式子末尾添加
			expression = expression + ((Button) v).getText();
			//记录上一次输入的字符
			last_input = (String) ((Button) v).getText();
			et_input.setText(expression);
			break;
		case R.id.btn_add:
		case R.id.btn_minus:
		case R.id.btn_multiple:
		case R.id.btn_divide:
			if (expression.length() == 12) {
				break;
			}
			//输入加减乘除，如果上一个输入的是加减乘除符号，这次点击无效
			if (last_input.equals("+") || last_input.equals("-") || last_input.equals("×") || last_input.equals("÷")) {
				
			}
			else {
				expression = expression + ((Button) v).getText();
				last_input = (String) ((Button) v).getText();
				et_input.setText(expression);
			}
			pointflag = false;
			break;
		case R.id.btn_del:
			//退格键，如果式子长度不为零，删除一个字符,否则不执行删除操作
			if (error1 == true) {
				et_input.setText(expression_backup);
				expression = expression_backup;
				error1 = false;
				break;
			}
			if(expression.length() > 1){
				//判断是否删除了小数点,是则取消小数点标记
				if(expression.substring(expression.length() - 2,expression.length() - 1).equals(".")){
					pointflag = false;
				}
				expression = expression.substring(0,expression.length() - 1);
				et_input.setText(expression);
			}
			else{
				expression = "0";
				et_input.setText(expression);
			}
			last_input = (String) ((Button) v).getText();
			break;
		case R.id.btn_clear:
			//删除整个式子，并且把所有标志归为初始状态
			expression = "0";
			expression_backup = "";
			last_input = "";
			first = 0;
			second = 0;
			et_input.setText(expression);
			pointflag = false;
			error1 = false;
			last_input = (String) ((Button) v).getText();
			break;
		case R.id.btn_point:
			if (expression.length() == 12) {
				break;
			}
			//输入小数点，如果一个数里面含有小数点，则不允许再输入小数点
			if(pointflag == false) {
				expression = expression + ((Button) v).getText();
				et_input.setText(expression);
				//表明已经输入小数点
				pointflag = true;
			}
			last_input = (String) ((Button) v).getText();
			break;	
		case R.id.btn_equal:
			getResult();
			last_input = (String) ((Button) v).getText();
			break;
		default:
			break;
		}
	}

	
	public void getResult(){
		//计算式子结果
		expression_backup = expression;
		
		//检查表达式里是否有运算符，如果没有则不需要进行运算
		int position1 = expression.indexOf("+");
		int position2 = expression.indexOf("-");
		int position3 = expression.indexOf("×");
		int position4 = expression.indexOf("÷");
		if(position1 < 0 && position2 < 0 && position3 < 0 && position4 < 0){
			return;
		}
		//一直做乘除
		while(doMultiplyAndDivide() != -1);
		//一直做加减
		while(doAddAndMinus() != -1);
		
		if (error1 == false) {
			double doubleResult = Double.parseDouble(expression);
			double intResult = Double.parseDouble(expression.substring(0, expression.indexOf('.')));
			if (doubleResult - intResult == 0) {
				expression = expression.substring(0, expression.indexOf('.'));
			}
			et_input.setText(expression);
			if(expression.indexOf('.') < 0){
				pointflag = false;
			}
			else{
				pointflag = true;
			}
		}
	}
	
	//计算乘除法
	public int doMultiplyAndDivide(){
		//计算乘除
		int position1 = 0;
		int position2 = 0;
		double temp = 0;
		int multilocation = expression.indexOf("×");
		int dividelocation = expression.indexOf("÷");
		String frontString = "";
		String backString = "";
		if (multilocation != -1 || dividelocation != -1){
			if (multilocation < dividelocation && multilocation != -1 && dividelocation != -1 || multilocation != -1 && dividelocation == -1){
				//做乘法
				position1 = getFirst(multilocation);
				if (position1 == multilocation) {
					position1 = -1;
				}
				position2 = getSecond(multilocation + 1);
				if(position2 == -1 ){
					position2 = expression.length();
				}
				Log.i("Tag", "position1: " + position1);
				Log.i("Tag", "position2: " + position2);
				//取得两个数字
				String s1 = expression.substring(position1 + 1,multilocation);
				String s2 = expression.substring(multilocation + 1,position2);
				
				first = Double.parseDouble(s1);
				if(s2 .equals("")){
					second = 1;
				}
				else{
					second = Double.parseDouble(s2);
				}
				
				temp  = first * second;
				Log.i("Tag", "s1: " + s1);
				Log.i("Tag", "s2: " + s2);
				Log.i("Tag", "temp: " + temp);
				if(position1 == -1){
					frontString = "";
				}
				else{
					//处理负数问题
					if(temp < 0){
						if(expression.substring(position1, position1).equals("-")){
							frontString = expression.substring(0,position1) + "+";
						}
						else{
							frontString = expression.substring(0,position1) + "-";
						}
					}
					else{
						frontString = expression.substring(0,position1 + 1);
					}
				}
				if (position2 == expression.length()) {
					backString = "";
				}
				else{
					backString = expression.substring(position2,expression.length());
				}
				expression = frontString + temp + backString;
				Log.i("Tag", "expression: " + expression);
			}
			else{
				//做除法
				position1 = getFirst(dividelocation);
				if (position1 == dividelocation) {
					position1 = -1;
				}
				position2 = getSecond(dividelocation + 1);
				if(position2 == -1){
					position2 = expression.length();
				}
				Log.i("Tag", "position1: " + position1);
				Log.i("Tag", "position2: " + position2);
				Log.i("Tag", "dividelocation: " + dividelocation);
				//取得两个数字
				String s1 = expression.substring(position1 + 1,dividelocation);
				String s2 = expression.substring(dividelocation + 1,position2);
				Log.i("Tag", "s1: " + s1);
				Log.i("Tag", "s2: " + s2);
				
				first = Double.parseDouble(s1);
				if(s2 .equals("")){
					second = 1;
				}
				else{
					second = Double.parseDouble(s2);
				}
				

				if (second - 0 == 0) {
					expression = "";
					Log.i("Tag", "zero");
					et_input.setText("ERROR ZERO");
					error1 = true;
					return -1;
				}
				temp  = first / second;
				if(position1 == -1){
					frontString = "";
				}
				else{
					//处理负数的问题 
					if(temp < 0){
						if(expression.substring(position1, position1).equals("-")){
							frontString = expression.substring(0,position1) + "+";
						}
						else{
							frontString = expression.substring(0,position1) + "-";
						}
					}
					else{
						frontString = expression.substring(0,position1 + 1);
					}
				}
				if (position2 == expression.length()) {
					backString = "";
				}
				else{
					backString = expression.substring(position2,expression.length());
				}

				expression = frontString + temp + backString;
				Log.i("Tag", "expression: " + expression);
			}
		}
		else{
			return -1;
		}
		return 0;
	}
	
	//做加法和减法
	public int doAddAndMinus(){
		
		int position1 = 0;
		int position2 = 0;
		double temp = 0;
		int addlocation = expression.indexOf("+");
		int minuslocation = expression.indexOf("-");
		String frontString = "";
		String backString = "";
		if (addlocation != -1 || minuslocation != -1){
			//按顺序做加减法
			if (addlocation < minuslocation && addlocation != -1 && minuslocation != -1 || addlocation != -1 && minuslocation == -1){
				//do add
				position1 = getFirst(addlocation);
				if (position1 == addlocation) {
					position1 = -1;
				}
				position2 = getSecond(addlocation + 1);
				if(position2 == -1){
					position2 = expression.length();
				}
				Log.i("Tag", "position1: " + position1);
				Log.i("Tag", "position2: " + position2);
				//取得两个数字
				String s1 = expression.substring(position1 + 1,addlocation);
				
				String s2 = expression.substring(addlocation + 1,position2);
					
				first = Double.parseDouble(s1);
				if(s2 .equals("")){
					second = 0;
				}
				else{
					second = Double.parseDouble(s2);
				}
				temp  = first + second;
				Log.i("Tag", "s1: " + s1);
				Log.i("Tag", "s2: " + s2);
				Log.i("Tag", "temp: " + temp);
				if(position1 == -1){
					frontString = "";
				}
				else{
					//处理负数问题
					if(temp < 0){
						if(expression.substring(position1, position1).equals("-")){
							frontString = expression.substring(0,position1) + "+";
						}
						else{
							frontString = expression.substring(0,position1) + "-";
						}
					}
					else{
						frontString = expression.substring(0,position1 + 1);
					}
				}
				if (position2 == expression.length()) {
					backString = "";
				}
				else{
					backString = expression.substring(position2,expression.length());
				}
				expression = frontString + temp + backString;
				Log.i("Tag", "expression: " + expression);
			}
			else{
				//做减法
				position1 = getFirst(minuslocation);
				if(position1 == 0){
					return -1;
				}
				if (position1 == minuslocation) {
					position1 = -1;
				}
				position2 = getSecond(minuslocation + 1);
				if(position2 == -1){
					position2 = expression.length();
				}
				Log.i("Tag", "position1: " + position1);
				Log.i("Tag", "position2: " + position2);
				Log.i("Tag", "minuslocation: " + minuslocation);
				//取得两个数字
				String s1 = expression.substring(position1 + 1,minuslocation);
				String s2 = expression.substring(minuslocation + 1,position2);
				Log.i("Tag", "s1: " + s1);
				Log.i("Tag", "s2: " + s2);
				first = Double.parseDouble(s1);
				if(s2 .equals("")){
					second = 0;
				}
				else{
					second = Double.parseDouble(s2);
				}
				temp  = first - second;
				if(position1 == -1){
					frontString = "";
				}
				else{
					//deal with negative 
					if(temp < 0){
						if(expression.substring(position1, position1).equals("-")){
							frontString = expression.substring(0,position1) + "+";
						}
						else{
							frontString = expression.substring(0,position1) + "-";
						}
					}
					else{
						frontString = expression.substring(0,position1 + 1);
					}
				}
				if (position2 == expression.length()) {
					backString = "";
				}
				else{
					backString = expression.substring(position2,expression.length());
				}

				Log.i("Tag", "temp: " + temp);
				expression = frontString + temp + backString;
				Log.i("Tag", "expression: " + expression);
			}
		}
		else{
			return -1;
		}
		return 0;
	}
	
	//取得运算符之前的运算符位置
	public int getFirst(int position)
	{
		int position1 = expression.indexOf("+",0);
		if (position1 > position) {
			position1 = -1;
		}
		int position2 = expression.indexOf("-",0);
		if (position2 > position) {
			position2 = -1;
		}
		return position1 > position2? position1: position2;
	}
	
	//取得运算符之后的运算符位置
	public int getSecond(int position)
	{
		int position1 = expression.indexOf("+",position);
		int position2 = expression.indexOf("-",position);
		int position3 = expression.indexOf("×",position);
		int position4 = expression.indexOf("÷",position);
		int small = getSmallNumber(position1, position2, position3, position4);
		return small;
	}
	
	//四个数比较大小，除去-1
	public int getSmallNumber(int a,int b,int c,int d){
		int array[] = {a,b,c,d};
		int min = -1;
		for(int i = 0;i < 4;i ++){
			if(array[i] != -1){
				if(min == -1){
					min = array[i];
					continue;
				}
				if(array[i] < min){
					min = array[i];
				}
			}
		}
		return min;
	}
}
