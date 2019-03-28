package com.example.vaibhav.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vaibhav.R;
import com.example.vaibhav.adapter.EmployeeAdapter;

public class MainActivity extends AppCompatActivity {

   RecyclerView employeeRV;
   EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeRV=findViewById(R.id.employeeRV);
        employeeRV.setHasFixedSize(true);
        employeeRV.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, AddEmployeeActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}
