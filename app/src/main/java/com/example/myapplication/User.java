package com.example.myapplication;

public class User {


    public  String id,name, surname,city, imgUri;

    public User() {
    }

    public String getImgUri() {
         return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public User(String id, String name, String surname, String city, String imageUri) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.imgUri = imageUri;

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
