package com.jk.webservicedemo.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.jk.webservicedemo.adapter.RecipeAdapter;
import com.jk.webservicedemo.databinding.ActivityMainBinding;
import com.jk.webservicedemo.models.CategoryContainer;
import com.jk.webservicedemo.models.Recipe;
import com.jk.webservicedemo.models.RecipeContainer;
import com.jk.webservicedemo.network.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = this.getClass().getCanonicalName();

    private ActivityMainBinding binding;
    private ArrayList<String> categoryList;
    private ArrayAdapter<String> adapter;
    String Category = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.categoryList = new ArrayList<>();
        this.getCategoryList();

        // Create an ArrayAdapter using the string array and a default spinner layout
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.categoryList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        this.binding.spnCategories.setAdapter(adapter);
        this.binding.spnCategories.setOnItemSelectedListener(this);

        this.binding.btnGetReceipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomRecipe();
            }
        });
    }

    private void getCategoryList(){
        Log.d(TAG, "getCategoryList: Getting category list");

        //get the instance  of Call<CategoryContainer>
        Call<CategoryContainer> categoryCall = RetrofitClient.getInstance().getApi().retrieveCategories();

        //execute the Call
        try{
            categoryCall.enqueue(new Callback<CategoryContainer>() {
                @Override
                public void onResponse(Call<CategoryContainer> call, Response<CategoryContainer> response) {

//                    if (response.isSuccessful()){
                    if (response.code() == 200){
                        //successfully received the response
                        CategoryContainer apiResponse = response.body();

                        if (!apiResponse.getCategories().isEmpty()) {
                            Log.e(TAG, "onResponse: CategoryContainer Received " + apiResponse.getCategories().size() + " objects received.");
                            Log.e(TAG, "onResponse: CategoryContainer : " + apiResponse.getCategories());

                            //use and process the data
                            categoryList.clear();
                            for(int i=0; i<apiResponse.getCategories().size(); i++){
                                categoryList.add(apiResponse.getCategories().get(i).getCategoryName());
                            }
                            adapter.notifyDataSetChanged(); //refresh the UI of spinner/RecyclerView once the data has changed

                        }else{
                            Log.e(TAG, "onResponse: Empty response received from server....Please check the structure of the data");
                        }
                    }else{
                        Log.e(TAG, "onResponse: Server responded with code " + response.code() );
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<CategoryContainer> call, Throwable t) {
                    call.cancel();
                    Log.e(TAG, "onFailure: Unable to get the data from API" + t.getLocalizedMessage() );
                }
            });

        }catch(Exception ex){
            Log.e(TAG, "getCategoryList: Unable to complete the categoryCall" + ex.getLocalizedMessage() );
        }
    }

    private void getRandomRecipe(){
        Log.d(TAG, "getRandomRecipe: Getting Random Recipe");
        Log.d(TAG, "Category = "+Category);

        Call<RecipeContainer> recipeCall = RetrofitClient.getInstance().getApi().getRecipeList(Category);

        try{
            recipeCall.enqueue(new Callback<RecipeContainer>() {
                @Override
                public void onResponse(Call<RecipeContainer> call, Response<RecipeContainer> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null){
                            RecipeContainer apiResponse = response.body();
                            if (apiResponse.getAllRecipe().isEmpty()){
                                Log.e(TAG, "onResponse: Empty response received from server for random recipe....Please check the structure of the data");
                            }else{
//                                Recipe recipe = apiResponse.getRandomRecipe().get(0);
//                                binding.tvReceipeName.setText(recipe.getRecipeName());
//                                binding.tvRegionName.setText(apiResponse.getRandomRecipe().get(0).getRegionName());
//                                Log.e(TAG, "onResponse: Image URL " + recipe.getImageURL() );
//                                Picasso.get().load(recipe.getImageURL()).into(binding.imgReceipeThumb);
//                                Glide.with(getApplicationContext()).load(recipe.getImageURL()).into(binding.imgReceipeThumb);
                                List<Recipe> recipeList = apiResponse.getAllRecipe();
                                binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                binding.recipeRecyclerView.setAdapter(new RecipeAdapter(MainActivity.this,recipeList));
                            }
                        }
                    }else{
                        Log.e(TAG, "onResponse: Server responded with code " + response.code() );
                    }
                    call.cancel();
                }
                @Override
                public void onFailure(Call<RecipeContainer> call, Throwable t) {
                    call.cancel();
                    Log.e(TAG, "onFailure: Unable to get the random recipe from API" + t.getLocalizedMessage() );
                }
            });
        }catch(Exception ex){
            Log.e(TAG, "getRandomRecipe: Unable to get random recipe " + ex.getLocalizedMessage() );
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemSelected: Selected Category of meal : " + categoryList.get(position));
        Category = categoryList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: Nothing selected from spinner");
        Category = "";
    }
}

//https://www.themealdb.com/api.php
//https://www.themealdb.com/api/json/v1/1/list.php?c=list
//https://www.themealdb.com/api/json/v1/1/random.php
//https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian