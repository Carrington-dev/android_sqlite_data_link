package com.stemgon.datalink.home.models;

public class User {
    private Integer id;
    private  String email, phone;


    public User(Integer id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
