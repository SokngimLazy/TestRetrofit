package com.example.macbookpro.retrofittesting.retrofit;

import com.example.macbookpro.retrofittesting.model.RESPONSE_OBJECT;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    //@FormUrlEncoded
    @POST("OTO/Api?")
    Call<RESPONSE_OBJECT> logout(@Body String JSONData);

}
