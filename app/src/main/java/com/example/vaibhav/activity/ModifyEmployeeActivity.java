package com.example.vaibhav.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vaibhav.R;
import com.example.vaibhav.database.DBManager;
import com.example.vaibhav.util.Utils;

import java.io.IOException;
import java.io.InputStream;

public class ModifyEmployeeActivity extends AppCompatActivity implements OnClickListener {
    private Button imgSelectBTN,updateRecord;
    private EditText nameET,mailET,numberET;
    private ImageView imageView;

    DBManager dbManager;

    private long _id;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_employee);

        dbManager = new DBManager(this);
        dbManager.open();


        imageView=findViewById(R.id.imageView);
        nameET=findViewById(R.id.nameET);
        mailET=findViewById(R.id.mailET);
        numberET=findViewById(R.id.numberET);
        imgSelectBTN=findViewById(R.id.imgSelectBTN);
        updateRecord=findViewById(R.id.update_record);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");
        byte[] img = intent.getByteArrayExtra("img");

        _id = Long.parseLong(id);
        nameET.setText(name);
        mailET.setText(email);
        numberET.setText(number);
        Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
        imageView.setImageBitmap(bmp);
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_record:
                final String name = nameET.getText().toString();
                final String mail = mailET.getText().toString();
                final String number =numberET.getText().toString();

                dbManager.update(_id, name, mail,number,saveImageInDB());
                this.returnHome();
                break;

            case R.id.imgSelectBTN:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                this.returnHome();
                break;
        }
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
