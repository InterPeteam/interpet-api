package com.interteam.interpet.api.model;

public class MUser {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String city;
    private String phone;
    private String text;
    private String role;

    public MUser(String id, String email, String name, String surname, String city, String phone, String text, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.phone = phone;
        this.text = text;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getText() {
        return text;
    }

    public String getRole() {
        return role;
    }
}
