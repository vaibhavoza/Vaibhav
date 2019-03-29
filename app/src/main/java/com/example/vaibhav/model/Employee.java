package com.example.vaibhav.model;

public class Employee {
    String id;
    String name,email,number;
    String img;

    public Employee() {
    }

    public Employee(String id, String name, String email, String number, String img) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.img = img;
    }

    public Employee(String name, String email, String number, String img) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
