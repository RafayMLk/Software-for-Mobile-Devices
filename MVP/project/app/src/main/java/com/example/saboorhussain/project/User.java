package com.example.saboorhussain.project;

import android.location.Location;
import android.provider.ContactsContract;

import com.google.android.gms.maps.model.LatLng;

public class User {

    public String Name;
    public String Mail;
    public String  Password;
    public int age;
    public String Gender;
    public String Bio;
    public String Contact;
    public String Profession;
    public String City;
    double latitude;
    double longitude;
    LatLng latLng;


    public User(String name, String mail, String password, int age, String gender, String bio, String contact, String profession, String city , double lat, double lng) {
        this.Name= name;
        this.Mail = mail;
        this.Password = password;
        this.age = age;
        this.Gender = gender;
        this.Bio = bio;
        this.Contact = contact;
        this.Profession = profession;
        this.City = city;
        this.latitude = lat;
        this.longitude= lng;
        latLng = new LatLng(latitude,longitude);
    }
}



