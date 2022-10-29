package com.example.projectnews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectnews.R;
import com.example.projectnews.model.CategoryRvModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewCategoryAdapter extends RecyclerView.Adapter<NewCategoryAdapter.ViewHolder> {
    private ArrayList<CategoryRvModal> categoryRvModals;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public NewCategoryAdapter(ArrayList<CategoryRvModal> categoryRvModals, Context context) {
        this.categoryRvModals = categoryRvModals;
        this.context = context;
    }

    public NewCategoryAdapter(ArrayList<CategoryRvModal> categoryRvModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRvModals = categoryRvModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public NewCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item, parent, false);
        return new NewCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRvModal categoryRvModal = categoryRvModals.get(position);
        holder.categoryTV.setText(categoryRvModal.getCategory());
        Picasso.get().load(categoryRvModal.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRvModals.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private ImageView categoryIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
            categoryIV = itemView.findViewById(R.id.idIVCategory);
        }
    }
}
