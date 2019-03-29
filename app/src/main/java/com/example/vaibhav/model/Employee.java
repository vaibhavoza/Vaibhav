package com.example.vaibhav.model;

public class Employee {
    String id;
    String name,email,number;
    byte[] img;

    public Employee() {
    }

    public Employee(String id, String name, String email, String number, byte[] img) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.img = img;
    }

    public Employee(String name, String email, String number, byte[] img) {
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
