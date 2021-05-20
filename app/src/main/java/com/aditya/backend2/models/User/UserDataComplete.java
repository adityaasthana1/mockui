package com.aditya.backend2.models.User;

import java.util.Date;

public class UserDataComplete {
    public String id;
    public String gender;
    public String firstName;
    public Location location;
    public String lastName;
    public String picture;
    public String phone;
    public String title;
    public String email;
    public Date registerDate;
    public Date dateOfBirth;

    public UserDataComplete(){}

    public UserDataComplete(String id, String gender, String firstName, Location location, String lastName, String picture, String phone, String title, String email, Date registerDate, Date dateOfBirth) {
        this.id = id;
        this.gender = gender;
        this.firstName = firstName;
        this.location = location;
        this.lastName = lastName;
        this.picture = picture;
        this.phone = phone;
        this.title = title;
        this.email = email;
        this.registerDate = registerDate;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
