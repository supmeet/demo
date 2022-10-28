package com.jk.webservicedemo.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeContainer {
    private @SerializedName("meals") ArrayList<Recipe> recipeList;

    public ArrayList<Recipe> getAllRecipe() {
        return recipeList;
    }

    @Override
    public String toString() {
        return "RecipeContainer{" +
                "randomRecipe=" + recipeList +
                '}';
    }
}
