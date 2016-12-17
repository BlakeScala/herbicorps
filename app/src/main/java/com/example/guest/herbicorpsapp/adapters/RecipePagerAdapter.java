package com.example.guest.herbicorpsapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.herbicorpsapp.models.Recipe;
import com.example.guest.herbicorpsapp.ui.RecipeDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/2/16.
 */
public class RecipePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Recipe> mRecipes;
    private String mSrc;

    public RecipePagerAdapter(FragmentManager fm, ArrayList<Recipe> recipes, String src) {
        super(fm);
        mRecipes = recipes;
        mSrc = src;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeDetailFragment.newInstance(mRecipes, position, mSrc);
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRecipes.get(position).getRecipeName();
    }
}
