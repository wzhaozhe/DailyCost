package com.zhaozhe.dailycost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private EditText amountNumber;
    private DBHandler dbHandler;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDate();

        //**********************
        amountNumber = findViewById(R.id.amountNumber);
        dbHandler = new DBHandler(MainActivity.this);
        dateTimeDisplay = (TextView) findViewById(R.id.textViewDate);
        addBtn = findViewById(R.id.testBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String strDate = date;
                String strAmount = amountNumber.getText().toString();
                Log.i("number", strAmount + "   " + strDate);

                if(strDate.isEmpty() && strAmount.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your daily cost", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewPayment(strDate, strAmount);

                Toast.makeText(MainActivity.this, "Payment has been added.", Toast.LENGTH_SHORT).show();
                date = "";
                amountNumber.setText("");
            }
        });
        //upLoaddata(date);
    }

    protected void getDate(){
        dateTimeDisplay = (TextView) findViewById(R.id.textViewDate);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);
        dateTimeDisplay = (TextView) findViewById(R.id.textViewWeek);
        dateFormat = new SimpleDateFormat("EEE");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);
    }
        //**************************
//    protected void upLoadData(String dbDate){
//        addBtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                String strDate = dbDate;
//                String strAmount = amountNumber.getText().toString();
//
//                if(strDate.isEmpty() && strAmount.isEmpty()){
//                    Toast.makeText(MainActivity.this, "Please enter your daily cost", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                dbHandler.addNewPayMent(strDate, strAmount);
//
//                Toast.makeText(MainActivity.this, "Payment has been added.", Toast.LENGTH_SHORT).show();
//                strAmount = "";
//                strDate = "";
//            }
//        });
//    }
}