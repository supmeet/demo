package com.jk.webservicedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jk.webservicedemo.R;
import com.jk.webservicedemo.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.SLViewHolder> {

    Context context;
    List<Recipe> recipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public SLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeAdapter.SLViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_holder,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SLViewHolder holder, int position) {
        Recipe rc = recipeList.get(position);
        holder.tv_receipe_name.setText("Recipe : " + rc.getRecipeName());
        holder.tv_region_name.setText("Region : " + rc.getRegionName());
//        Picasso.get().load(rc.getImageURL()).into(holder.img_receipe_thumb);
        Glide.with(context).load(rc.getImageURL()).into(holder.img_receipe_thumb);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class SLViewHolder extends RecyclerView.ViewHolder {

        TextView tv_receipe_name, tv_region_name;
        ImageView img_receipe_thumb;

        public SLViewHolder(@NonNull View itemView) {
            super(itemView);

            img_receipe_thumb = itemView.findViewById(R.id.img_receipe_thumb);
            tv_receipe_name = itemView.findViewById(R.id.tv_receipe_name);
            tv_region_name = itemView.findViewById(R.id.tv_region_name);
        }
    }
}
