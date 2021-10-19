package com.example.myapplication;

import android.graphics.Bitmap;

public class NewGroupData {
    String id;
    String title;
    String decription;
    String interest;
    String category;
    String imageUri;

    public NewGroupData() {

    }
    public NewGroupData(String id, String title, String decription , String interest, String category, String imageUri) {

        this.id = id;
        this.title = title;
        this.decription = decription;
        this.interest = interest;
        this.category = category;
        this.imageUri= imageUri;

    }


    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDecription() {
        return decription;
    }

    public String getInterest() {
        return interest;
    }

    public String getCategory() {
        return category;
    }
    public String getImageUri() {
        return imageUri;
    }

}
