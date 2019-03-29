package com.example.vaibhav.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.R;
import com.example.vaibhav.activity.MainActivity;
import com.example.vaibhav.activity.ModifyEmployeeActivity;
import com.example.vaibhav.database.DBManager;
import com.example.vaibhav.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{

    Context context;
    List<Employee> employeeList;
    private DBManager dbManager;
    private long _id;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
        dbManager = new DBManager(context);
        dbManager.open();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.emplyee_list_item, viewGroup, false);

        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int i) {
        final Employee employee=employeeList.get(i);

        holder.empName.setText(employee.getName());
        Bitmap bmp = BitmapFactory.decodeByteArray(employee.getImg(), 0, employee.getImg().length);
        holder.empImg.setImageBitmap(bmp);

        holder.empName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ModifyEmployeeActivity.class);
                intent.putExtra("id",employee.getId());
                intent.putExtra("name",employee.getName());
                intent.putExtra("email",employee.getEmail());
                intent.putExtra("number",employee.getNumber());
                intent.putExtra("img",employee.getImg());
                context.startActivity(intent);
            }
        });

        holder.delBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMethod(v,employee);
//                Toast.makeText(context,"Delete btn:"+employee.getId(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteMethod(View v, Employee employee) {
        _id = Long.parseLong(employee.getId());
        dbManager.delete(_id);
        updateEmplyoeeList(employeeList);
        //notifyItemRangeChanged(0,employeeList.size());
    }

    private void updateEmplyoeeList(List<Employee> newemployeeList) {
        employeeList.clear();
        employeeList.addAll(newemployeeList);
        notifyDataSetChanged();
        context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NO_ANIMATION));

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
