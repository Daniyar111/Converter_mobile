package com.example.saint.convertproject.data.network;

import com.example.saint.convertproject.data.entity.CurrencyGetter;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by saint on 18.03.2018.
 */

public interface RetrofitService {

    @GET("latest")
    Call<CurrencyGetter> getCurrencies();
}
