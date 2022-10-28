package com.jk.webservicedemo.models;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    private @SerializedName("strMeal") String recipeName;
    private @SerializedName("strArea") String regionName;
    private @SerializedName("strMealThumb") String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRegionName() {
        return regionName;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeName='" + recipeName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
