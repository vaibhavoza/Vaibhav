package com.example.vaibhav.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vaibhav.R;
import com.example.vaibhav.model.DBManager;

public class AddEmployeeActivity extends AppCompatActivity {

    private Button imgSelectBTN,addRecord;
    private EditText nameET,mailET,numberET;
    private ImageView imageView;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        imageView=findViewById(R.id.imageView);
        nameET=findViewById(R.id.nameET);
        mailET=findViewById(R.id.mailET);
        numberET=findViewById(R.id.numberET);
        imgSelectBTN=findViewById(R.id.imgSelectBTN);
        addRecord=findViewById(R.id.addRecord);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");
        String img = intent.getStringExtra("email");

    }
}
