package com.example.guest.herbicorpsapp;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 12/1/16.
 */
public class YummlyService {

    public static void findRecipes(String foodEntry, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.APP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.APP_ID_QUERY_PARAMETER, Constants.APP_ID);
        urlBuilder.addQueryParameter(Constants.APP_KEY_QUERY_PARAMETER, Constants.APP_KEY);
        urlBuilder.addQueryParameter(Constants.APP_FOOD_QUERY_PARAMETER, foodEntry);
    }
}













//http://api.yummly.com/v1/api/recipes?_app_id=98609b53&_app_key=765f89e001c44fd2193e307b33c12402&q=onion+soup &allowedDiet[]=386^Vegan
