package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http.dto;

import java.util.List;

/*
{
    "code": "9999",
    "name": "Papel",
    "price": 50,
    "categories": [
        "papeleria"
    ],
    "_id": "619f84ee17673968c862d500",
    "__v": 0
}
 */
public class ProductResponse {
    private String code;
    private String name;
    private Integer price;
    private List<String> categories;
    private String imageUrl;
    private String _id;
    private String __v;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void set__v(String __v) {
        this.__v = __v;
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

    @Override
    public String toString() {
        return "ProductResponse{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
