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
	boolean clear_flag = false;//清空
	
	double x1 = 0,x2 = 0;//定义两个变量
	
	String first = ""; //存储第一个要进行运算的字符
	String second = "";//存储第二个要进行运算的字符
	String expression = "";//存储输入的式子
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("hahahhaa");
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
			expression = expression + ((Button) v).getText();
			et_input.setText(expression);
			break;
		default:
			break;
		}
	}

	
	public void getResult(){
		
	}
}
