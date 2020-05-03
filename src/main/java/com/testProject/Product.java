package com.testProject;


public class Product {

    private Integer id;
    private String type;
    private String desc;

    public Product() {
    }

    public Product(int id, String type, String desc) {
        this.id = id;
        this.type = type;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}