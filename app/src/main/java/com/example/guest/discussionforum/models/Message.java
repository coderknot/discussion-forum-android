package com.example.guest.discussionforum.models;

/**
 * Created by Guest on 6/5/17.
 */

public class Message {
    String title;
    String content;

    public Message() { }

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
