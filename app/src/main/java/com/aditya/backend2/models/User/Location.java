package com.aditya.backend2.models.User;

public class Location {

    public String street;
    public String state;
    public String timezone;
    public String city;
    public String country;

    public Location(){}

    public Location(String street, String state, String timezone, String city, String country) {
        this.street = street;
        this.state = state;
        this.timezone = timezone;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
