package com.example.root.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    private Button calculatorButton;
    private EditText weightEditText;
    private CheckBox manCheckBox;
    private CheckBox womanCheckBox;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButton=(Button)findViewById(R.id.calculator);
        weightEditText=(EditText)findViewById(R.id.weight);
        manCheckBox=(CheckBox)findViewById(R.id.man);
        womanCheckBox=(CheckBox)findViewById(R.id.woman);
        resultTextView=(TextView)findViewById(R.id.result);
    }

    protected void onStart() {
        super.onStart();
        registerEvent();

    }
    private void registerEvent() {
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!weightEditText.getText().toString().trim().equals("")) {
                    if(manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight=Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("===评估结果====");
                        if(manCheckBox.isChecked()) {
                            sb.append("男性标准身高:");
                            double result=evaluateHeight(weight,"男");
                            sb.append((int)result+"(厘米)");
                        }
                        if(womanCheckBox.isChecked()) {
                            sb.append("女性标准身高:"):
                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"(厘米)");
                        }
                    }else {
                        showMessage("选择性别");
                    }
                }else {
                    showMessage("请输入体重");
                }
            }
        });
    }
    private double evaluateHeight(double weight,String sex) {
        double height;
        if(sex=="男") {
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    private void showMessage(String message) {
        AlertDialog alert= new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        alert.show();
    }

    public boolean onCreateOptionMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())  {
            case 1:
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
