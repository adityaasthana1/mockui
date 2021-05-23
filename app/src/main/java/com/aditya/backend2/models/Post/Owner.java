package com.aditya.backend2.models.Post;

import android.os.Parcel;
import android.os.Parcelable;

public class Owner implements Parcelable {
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

    protected Owner(Parcel in) {
        id = in.readString();
        email = in.readString();
        title = in.readString();
        picture = in.readString();
        firstName = in.readString();
        lastName = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(title);
        dest.writeString(picture);
        dest.writeString(firstName);
        dest.writeString(lastName);
    }
}
