package com.example.wx001.domain;

public class Company {
    private  Integer id;
    private  String comName;
    private  String comAddress;
    private  String comTel;

    public Company() {
    }

    public Company(Integer id, String comName, String comAddress, String comTel) {
        this.id = id;
        this.comName = comName;
        this.comAddress = comAddress;
        this.comTel = comTel;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComTel() {
        return comTel;
    }

    public void setComTel(String comTel) {
        this.comTel = comTel;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }
}
