package com.jk.webservicedemo.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    private @SerializedName("strCategory") String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

    //    String strCategoryDescription;
//    int id;
}
