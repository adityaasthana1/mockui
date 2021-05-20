package com.aditya.backend2.models.Post;

public class Owner {
    public String id;
    public String email;
    public String title;
    public String picture;
    public String firstName;
    public String lastName;

    public Owner(){}

    public Owner(String id, String email, String title, String picture, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
