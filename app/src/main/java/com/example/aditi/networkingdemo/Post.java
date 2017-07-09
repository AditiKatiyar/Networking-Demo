package com.example.aditi.networkingdemo;

/**
 * Created by Aditi on 7/8/2017.
 */

public class Post {
    int userId;
    int id;
    String title;
    String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
