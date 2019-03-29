package com.example.vaibhav.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vaibhav.R;
import com.example.vaibhav.database.DBManager;
import com.example.vaibhav.util.Utils;

import java.io.IOException;
import java.io.InputStream;

public class AddEmployeeActivity extends AppCompatActivity {

    private Button imgSelectBTN,addRecord;
    private EditText nameET,mailET,numberET;
    private ImageView imageView;

    private long _id;

    private DBManager dbManager;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";

    private Uri selectedImageUri;

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

//        Intent intent = getIntent();
//        String id = intent.getStringExtra("id");
//        String name = intent.getStringExtra("name");
//        String email = intent.getStringExtra("email");
//        String number = intent.getStringExtra("number");
//        String img = intent.getStringExtra("email");

        dbManager=new DBManager(this);
        dbManager.open();

        imgSelectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameET.getText().toString();
                final String mail = mailET.getText().toString();
                final String number =numberET.getText().toString();


                dbManager.insert(name, mail,number,saveImageInDB());

                Intent main = new Intent(AddEmployeeActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }
    byte[] saveImageInDB() {

        try {

            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
//            dbManager.insertImage(inputData);
//            dbManager.close();
            return inputData;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbManager.close();
            return null;
        }

    }
}
