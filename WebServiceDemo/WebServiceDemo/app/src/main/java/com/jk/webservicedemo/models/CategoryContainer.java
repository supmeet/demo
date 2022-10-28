package com.jk.webservicedemo.models;

import java.util.ArrayList;

public class CategoryContainer {

    //use @SerializedName() if JSON property naem and Java property name is different
//    private @SerializedName("categories") ArrayList<Category> categoryList;
    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "CategoryContainer{" +
                "categories=" + categories +
                '}';
    }
}

