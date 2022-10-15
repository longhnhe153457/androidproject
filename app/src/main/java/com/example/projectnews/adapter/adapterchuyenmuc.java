package com.example.projectnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectnews.R;
import com.example.projectnews.model.TaiKhoan;
import com.example.projectnews.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {
    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> chuyenmucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucList = chuyenmucList;
    }

    private Context context;
    private  int layout;
    private List<chuyenmuc> chuyenmucList;
    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(layout,null);
        ImageView img = (ImageView) view.findViewById(R.id.imgchuyemuc);

        TextView txt =(TextView) view.findViewById(R.id.textviewTenbaochuyenmuc);

        chuyenmuc cm = chuyenmucList.get(i);
        img.setImageResource(cm.getHinhanhchuyenmucl());
        txt.setText(cm.getTenchuyenmuc());
        img.setImageResource(cm.getHinhanhchuyenmucl());

        return  view;
    }
}
