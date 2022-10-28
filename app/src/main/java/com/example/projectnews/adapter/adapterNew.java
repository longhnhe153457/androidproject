package com.example.projectnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectnews.R;
import com.example.projectnews.model.New;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterNew extends BaseAdapter {
    private Context context;
    private ArrayList<New> listnew;
    @Override
    public int getCount() {
        return listnew.size();
    }

    @Override
    public Object getItem(int i) {
        return listnew.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public  class ViewHolder{
        TextView txtTenbao;
        ImageView imbBao;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        viewHolder=new ViewHolder();

        LayoutInflater infla =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = infla.inflate(R.layout.newbao,null);
        viewHolder.txtTenbao=view.findViewById(R.id.textviewTenbaoNew);
        viewHolder.imbBao=view.findViewById(R.id.imgnew);
        view.setTag(viewHolder);
        New news=(New) getItem(i);
        viewHolder.txtTenbao.setText(news.getCategory());

        Picasso.get().load(news.getImageLink()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_24).into(viewHolder.imbBao);

    return  view;

    }


    public adapterNew(Context context, ArrayList<New> listnew) {
        this.context = context;
        this.listnew = listnew;
    }
}
