package com.example.saint.convertproject;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saint on 18.03.2018.
 */

public class StartApplication extends Application {

    private static final String BASE_URL = "http://api.fixer.io/";
    private RetrofitService retrofitService;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitService = initRetrofit();
    }

    private RetrofitService initRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.class);
    }

    public RetrofitService getRetrofitService(){
        return retrofitService;
    }

    private OkHttpClient getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public static StartApplication get(Context context){
        return (StartApplication) context.getApplicationContext();
    }
}
