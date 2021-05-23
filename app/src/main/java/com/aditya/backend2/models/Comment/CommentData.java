package com.aditya.backend2.models.Comment;

import com.aditya.backend2.models.Post.Owner;

import java.util.Date;

public class CommentData {
    public Owner owner;
    public String id;
    public String message;
    public Date publishDate;

    public CommentData(){}

    public CommentData(Owner owner, String id, String message, Date publishDate) {
        this.owner = owner;
        this.id = id;
        this.message = message;
        this.publishDate = publishDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}


