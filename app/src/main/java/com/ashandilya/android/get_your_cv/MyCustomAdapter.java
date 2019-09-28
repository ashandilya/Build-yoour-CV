package com.ashandilya.android.get_your_cv;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shiraz.get_your_cv.R;

public class MyCustomAdapter extends BaseAdapter {
    String[] l_names;
    int[] imageos;
    Context c;

    LayoutInflater li;      //LayoutInflater is android System Service Convert XML to Java Object

    MyCustomAdapter(String[] l_names,int[] imageos,ListviewActivity meghna){

        c=meghna;
        li=LayoutInflater.from(c);//from() takes Context as an argument and returns LayoutInflater reference
        this.l_names=l_names;
        this.imageos=imageos;
    }

    @Override
    public int getCount() {
        return l_names.length;
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //inflate method says What you want to inflate and where you want to inflate your View
        MyViewHolder holder=null;
        if(convertView==null) {
            convertView = li.inflate(R.layout.layout_c_adapter, null);
            holder=new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(MyViewHolder) convertView.getTag();
        }

        holder.tv_type.setText(l_names[position]);
        holder.imageView.setImageResource(imageos[position]);
        return convertView;
    }

    class MyViewHolder{
        TextView tv_type;
        ImageView imageView;

        MyViewHolder(View v){
            tv_type=(TextView)v.findViewById(R.id.tv_type);
            imageView=(ImageView)v.findViewById(R.id.iv);
        }
    }
}

