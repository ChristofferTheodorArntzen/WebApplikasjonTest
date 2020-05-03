package com.testProject;

public class CreateProductRequest {

    String type;
    String desc;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
