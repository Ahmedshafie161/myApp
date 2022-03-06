package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pojo.MyAggregateData;

import java.util.ArrayList;

public class ShafieAdapter extends BaseAdapter {
    Context myContext ;
    ArrayList<MyAggregateData> data ;

    public ShafieAdapter(Context context, ArrayList<MyAggregateData>data){
        this.data=data;
        this.myContext=context;

    }
    public void addItem(MyAggregateData mA){
        this.data.add(mA);
    }
    public int getCount() {
        return data.size();
    }

    @Override
    public MyAggregateData getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =  convertView;
        if (v==null){


            v=LayoutInflater.from(myContext).inflate(R.layout.listview_layout,null,false);


        }
        //inflate
        TextView tvName= v.findViewById(R.id.tvName);
        TextView tvFollow= v.findViewById(R.id.tvFollow);
        TextView tvPost= v.findViewById(R.id.tvPost);

        //assign

        tvName.setText(data.get(position).getName());
        tvFollow.setText(data.get(position).getFollowNum()+"");
        tvPost.setText(data.get(position).getPostNum()+"");


        return v;

    }
}
