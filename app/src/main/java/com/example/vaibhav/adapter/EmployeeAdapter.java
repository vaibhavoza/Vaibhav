package com.example.vaibhav.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.R;
import com.example.vaibhav.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{

    Context context;
    List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.emplyee_list_item, viewGroup, false);

        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
        Employee employee=employeeList.get(i);


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        ImageView empImg;
        TextView empName;
        ImageView delBTN;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            empImg=itemView.findViewById(R.id.empImg);
            empName=itemView.findViewById(R.id.empName);
            delBTN=itemView.findViewById(R.id.delBTN);
        }
    }
}
