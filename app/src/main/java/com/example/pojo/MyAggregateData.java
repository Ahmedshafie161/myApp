package com.example.pojo;

import java.io.Serializable;

public class MyAggregateData implements Serializable {
    private String name;
    private int followNum;
    private int postNum;

    public MyAggregateData(String name, int followNum, int postNum) {
        this.name = name;
        this.followNum = followNum;
        this.postNum = postNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }
}
