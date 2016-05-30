package com.sheeloh.alhusban.sheelohapp.ServerRelevants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hashim Al-husban
 * on 25/04/16.
 */

public final class User extends Tags {
    private String ApiToken;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String Phone;
    private int Gender;
    private int Country;
    private String CountryName;
    private String CountryNameS;
    private String GenderName;
    private String GenderNameS;
    private String Language;
    private int Age;
    private int IsActive;
    private int UserId;

    public User() {
    }

    public User(String data) {
        this();
        ValidateData(data);
    }

    public void ValidateData(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            if (obj.has(USER_FIRSTNAME)) setFirstName(obj.getString(USER_FIRSTNAME));
            if (obj.has(USER_LASTNAME)) setLastName(obj.getString(USER_LASTNAME));
            if (obj.has(USER_EMAIL)) setEmail(obj.getString(USER_EMAIL));
            if (obj.has(USER_AGE)) setAge(obj.getInt(USER_AGE));
            if (obj.has(USER_COUNTRY)) setCountry(obj.getInt(USER_COUNTRY));
            if (obj.has(USER_PASSWORD)) setPassword(obj.getString(USER_PASSWORD));
            if (obj.has(USER_PHONE)) setPhone(obj.getString(USER_PHONE));
            if (obj.has(USER_LANGUAGE)) setLanguage(obj.getString(USER_LANGUAGE));
            if (obj.has(USER_GENDER)) setGender(obj.getInt(USER_GENDER));
            if (obj.has(USER_GENDERNAME)) setGenderName(obj.getString(USER_GENDERNAME));
            if (obj.has(USER_GENDERNAMES)) setGenderNameS(obj.getString(USER_GENDERNAMES));
            if (obj.has(USER_COUNTRY)) setCountry(obj.getInt(USER_COUNTRY));
            if (obj.has(USER_COUNTRYNAME)) setCountryName(obj.getString(USER_COUNTRYNAME));
            if (obj.has(USER_COUNTRYNAMES)) setCountryNameS(obj.getString(USER_COUNTRYNAMES));
            if (obj.has(USER_APITOKEN)) setCountryNameS(obj.getString(USER_APITOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getApiToken() {
        return ApiToken;
    }

    public void setApiToken(String apiToken) {
        ApiToken = apiToken;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getCountry() {
        return Country;
    }

    public void setCountry(int country) {
        Country = country;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    private String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryNameS() {
        return CountryNameS;
    }

    public void setCountryNameS(String countryNameS) {
        CountryNameS = countryNameS;
    }

    private String getGenderName() {
        return GenderName;
    }

    public void setGenderName(String genderName) {
        GenderName = genderName;
    }

    public String getGenderNameS() {
        return GenderNameS;
    }

    public void setGenderNameS(String genderNameS) {
        GenderNameS = genderNameS;
    }

    @Override
    public String toString() {

        if (getGenderName() == null || getCountryName() == null)
            return " { Email='" + getEmail() + '\'' +
                    ", FirstName='" + getFirstName() + '\'' +
                    ", LastName='" + getLastName() + '\'' +
                    ", Password='" + getPassword() + '\'' +
                    ", Phone='" + getPhone() + '\'' +
                    ", Gender='" + getGender() + '\'' +
                    ", Country='" + getCountry() + '\'' +
                    ", Age='" + getAge() + '\'' +
                    ", Language='" + getLanguage() + '\'' +
                    ", api_token='" + getApiToken() + '\'' +
                    '}';

        return " { Email='" + getEmail() + '\'' +
                ", FirstName='" + getFirstName() + '\'' +
                ", LastName='" + getLastName() + '\'' +
                ", Password='" + getPassword() + '\'' +
                ", Phone='" + getPhone() + '\'' +
                ", Gender='" + getGender() + '\'' +
                ", Gender_Name='" + getGenderName() + '\'' +
                ", Gender_Name_S='" + getGenderNameS() + '\'' +
                ", Country='" + getCountry() + '\'' +
                ", Country_Name='" + getCountryName() + '\'' +
                ", Country_Name_S='" + getCountryNameS() + '\'' +
                ", Language='" + getLanguage() + '\'' +
                ", Age='" + getAge() + '\'' +
                ", api_token='" + getApiToken() + '\'' +
                '}';

    }

    public String getInsertMethod() {
        return "( '" + getEmail() + '\'' +
                ", '" + getPassword() + '\'' +
                ", '" + getFirstName() + '\'' +
                ", '" + getLastName() + '\'' +
                ", " + getPhone() +
                ", " + 0 +
                ", " + getGender() +
                ", " + getCountry() +
                (getLanguage() != null ? (", " + getLanguage()) : ("")) +
                ", " + getAge() +
                ')';


    }

}
