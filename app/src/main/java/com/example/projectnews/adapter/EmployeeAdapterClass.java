package com.example.projectnews.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectnews.R;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.model.TaiKhoan;

import java.util.List;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder> {

    List<TaiKhoan> employee;
    Context context;
    DBHelper databaseHelperClass;

    public EmployeeAdapterClass(List<TaiKhoan> employee, Context context) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TaiKhoan employeeModelClass = employee.get(position);

        holder.textViewID.setText(employeeModelClass.getUsername());
        holder.editText_Name.setText(employeeModelClass.getEmail());
        holder.editText_Email.setText("Nguời dùng");
        byte[] anh = employeeModelClass.getImg();

        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.anou);

        if(bitmap == null){
            holder.imgview.setImageBitmap(icon);
        }
        else {
            holder.imgview.setImageBitmap(bitmap);
        }


        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteEmployee(employeeModelClass.getUsername());
                employee.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgview;
        TextView textViewID;
        EditText editText_Name;
        EditText editText_Email;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            button_delete = itemView.findViewById(R.id.button_delete);
            imgview= itemView.findViewById(R.id.imgHinhDaiDien);

        }
    }
}