package com.example.saint.convertproject;

import android.app.Application;
import android.content.Context;

import com.example.saint.convertproject.config.Constants;
import com.example.saint.convertproject.data.network.NetworkBuilder;
import com.example.saint.convertproject.data.network.RetrofitService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saint on 18.03.2018.
 */

public class StartApplication extends Application {

    private RetrofitService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initRetrofitClient();
    }

    public static StartApplication get(Context context){
        return (StartApplication) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }
}
