package com.example.saboorhussain.project;

import android.location.Location;
import android.media.Image;
import android.provider.ContactsContract;

import com.google.android.gms.maps.model.LatLng;

public class User {

    private String uid;
    private String name;
    private String mail;
    private String  password;
    private String  imageaddress;
    private String age;
    private String contact;
    private String proffession;
    private String latitude;
    private String longitude;

    public User() {
    }


    public User(String uid, String mail, String password , String imageaddress, String name , String age , String contact ,String proffession,String latitude, String longitude) {
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.imageaddress = imageaddress;
        this.age = age;
        this.contact = contact;
        this.proffession = proffession;
        this.latitude = latitude;
        this.longitude = longitude;

    }
    public String getUid() {
        return this.uid;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getProffession() {
        return proffession;
    }

    public String getMail() {
        return this.mail;
    }

    public String getPassword() {
        return this.password;
    }

    public String getImageaddress() {
        return imageaddress;
    }
}



