package com.example.guest.herbicorpsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.herbicorpsapp.R;
import com.example.guest.herbicorpsapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private ArrayList<Recipe> mRecipes = new ArrayList<Recipe>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recipeNameTextView) TextView mRecipeNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        @Bind(R.id.recipeImageView) ImageView mRecipeImageView;
        @Bind(R.id.estimatedTimeTextView) TextView mEstimatedTimeTextView;

        private Context context;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRecipe(Recipe recipe) {
            mRecipeNameTextView.setText(recipe.getRecipeName());
            mRatingTextView.setText("Rating: " + String.valueOf(recipe.getRating()) + "/5");
            mEstimatedTimeTextView.setText("Time: " + String.valueOf(recipe.getEstimatedTime()/60) + " minutes");
            Picasso.with(mContext).load(recipe.getImageURL()).into(mRecipeImageView);
        }
    }
}
