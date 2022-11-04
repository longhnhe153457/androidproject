package com.example.projectnews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectnews.NewDetailActivity;
import com.example.projectnews.R;
import com.example.projectnews.model.New;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    Context context;
    ArrayList<New> newArrayList;

    public NewAdapter(Context context, ArrayList<New> newArrayList) {
        this.context = context;
        this.newArrayList = newArrayList;
    }
    public void setData(ArrayList<New> list){
        this.newArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_new_item, parent, false);
        return new NewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        New newObj = newArrayList.get(position);
        holder.titleTV.setText(newObj.getTitle());
        holder.subtitleTV.setText(newObj.getContent());
        Picasso.get().load(newObj.getImageLink()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_24).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewDetailActivity.class);
                intent.putExtra("newId", Integer.toString(newArrayList.get(position).getID()));
                intent.putExtra("newTitle", newArrayList.get(position).getTitle());
                intent.putExtra("newCategory", newArrayList.get(position).getCategory());
                intent.putExtra("newContent", newArrayList.get(position).getContent());
                intent.putExtra("newImageLink", newArrayList.get(position).getImageLink());
                intent.putExtra("newAuthor", newArrayList.get(position).getAuthor());
                intent.putExtra("newCreateDate", newArrayList.get(position).getCreateDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTV, subtitleTV;
        ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTV = itemView.findViewById(R.id.idTVSubTitle);
            newsIV = itemView.findViewById(R.id.idIViews);
        }
    }
}
