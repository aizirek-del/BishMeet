package com.example.myapplication;

public class User {


    public  String id,name, surname,city, imageUri;

    public User() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public User(String id, String name, String surname, String city, String imageUri) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.imageUri = imageUri;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
