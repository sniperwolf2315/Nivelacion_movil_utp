package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http.dto;

import java.util.List;

/*
{
    "code": "9999",
    "name": "Papel",
    "price": 50,
    "categories": [
        "papeleria"
    ]
}
 */
public class ProductRequest {
    private String code;
    private String name;
    private Integer price;
    private List<String> categories;
    private String imageUrl;

    public ProductRequest() {
    }

    public ProductRequest(String code, String name, Integer price, List<String> categories) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public ProductRequest(String code, String name, Integer price, List<String> categories, String imageUrl) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
