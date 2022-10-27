package com.example.projectnews.adapter;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectnews.R;
import com.example.projectnews.model.TaiKhoan;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class adapterthongtin extends BaseAdapter {


    public adapterthongtin(Context context, int layout, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }

    private Context context;
    private  int layout;
    private List<TaiKhoan> taiKhoanList;
    @Override
    public int getCount() {
        return taiKhoanList.size();
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
        view= inflater.inflate(layout,null);



        TextView txtTenTaiKhoan =(TextView) view.findViewById(R.id.TEXT_NAME);
        TextView txtGamil =(TextView) view.findViewById(R.id.Text_gmail);

        TaiKhoan taiKhoan=taiKhoanList.get(i);
        txtTenTaiKhoan.setText(taiKhoan.getUsername());
        txtGamil.setText(taiKhoan.getEmail());

//        Bitmap bitmap = BitmapFactory.decodeByteArray(taiKhoan.getImg(), 0, taiKhoan.getImg().length);
//
//        Bitmap icon = BitmapFactory.decodeResource(Resources.getSystem(),
//                R.drawable.anou);
//        if(bitmap == null){
//            img.setImageBitmap(icon);
//        }
//        else {
//            img.setImageBitmap(bitmap);
//        }


        return view;
    }
    private byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
