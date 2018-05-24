package com.example.saint.convertproject.data.network;

import android.support.annotation.NonNull;

import com.example.saint.convertproject.config.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkBuilder {

    private static RetrofitService sService;

    public static RetrofitService initRetrofitClient(){

        if(sService == null){
            sService = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build()
                    .create(RetrofitService.class);
        }
        return sService;
    }

    private static OkHttpClient getClient(){

        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {

                        Request.Builder ongoing = chain.request()
                                .newBuilder()
                                .addHeader("Accept", "application/json;versions=1");

                        return chain.proceed(ongoing.build());
                    }
                })
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }
}
