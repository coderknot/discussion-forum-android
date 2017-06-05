package com.example.guest.discussionforum.models;

/**
 * Created by Guest on 6/5/17.
 */

public class Message {
    String title;
    String content;
    String category;

    public Message() { }

    public Message(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getCategory() {
        return this.category;
    }
}
