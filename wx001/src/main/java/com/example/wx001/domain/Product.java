package com.example.wx001.domain;

public class Product {
    private Integer id;
    private Integer companyId;
    private Double weight;
    private Double volume;
    private String pname;
    private String unit;

    private String comName;

    public Product(){

    }

    public Product(Integer id, Integer companyId, Double weight, Double volume, String pname, String unit) {
        this.id = id;
        this.companyId = companyId;
        this.weight = weight;
        this.volume = volume;
        this.pname = pname;
        this.unit = unit;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
