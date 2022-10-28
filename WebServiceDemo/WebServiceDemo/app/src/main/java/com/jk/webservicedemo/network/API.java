package com.jk.webservicedemo.network;

import com.jk.webservicedemo.models.CategoryContainer;
import com.jk.webservicedemo.models.RecipeContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
//    base URL must ends with /
    //base URL must use HTTP Secured
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

//    @GET("./list.php?a=list")
    @GET("./categories.php")
    Call<CategoryContainer> retrieveCategories();

//    @GET("./random.php")
//    Call<RecipeContainer> retrieveRandomRecipe();

//    www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("./filter.php")
    Call<RecipeContainer> getRecipeList(@Query("c") String category);

//    http://api.weatherapi.com/v1/current.json?key=e2b9e47b5b9140db97450252212903&q=Toronto

//    @GET("./current.json?key={api_key}&q={city_name}")
//    Call<Weather> retrieveWeatherInfo(@Path("api_key") String key, @Path("city_name") String location);
}















