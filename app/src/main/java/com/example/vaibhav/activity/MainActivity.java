package com.example.vaibhav.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vaibhav.R;
import com.example.vaibhav.adapter.EmployeeAdapter;
import com.example.vaibhav.database.DBManager;
import com.example.vaibhav.database.DatabaseHelper;
import com.example.vaibhav.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   RecyclerView employeeRV;
   EmployeeAdapter adapter;
   List<Employee> employeeList;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager=new DBManager(this);
        dbManager.open();

        employeeList=new ArrayList<>();
        employeeList.addAll(dbManager.getAllUser());

        adapter=new EmployeeAdapter(this,employeeList);

        employeeRV=findViewById(R.id.employeeRV);
        employeeRV.setHasFixedSize(true);
        employeeRV.setItemAnimator(new DefaultItemAnimator());
        employeeRV.setLayoutManager(new LinearLayoutManager(this));
        employeeRV.setAdapter(adapter);
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
