package com.example.pojo;

public class PostModel {
    private int id;
    private String body;
    private String title;

    public PostModel(int id, String body, String title) {
        this.id = id;
        this.body = body;
        this.title = title;
    }

    public PostModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
