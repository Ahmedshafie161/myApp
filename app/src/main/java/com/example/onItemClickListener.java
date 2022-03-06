package com.example;


import com.example.pojo.MyAggregateData;
import com.example.pojo.PostModel;

public interface onItemClickListener {

    void onItemClick(MyAggregateData arrayAggr );
    void onItemClick(PostModel postModel );
}
