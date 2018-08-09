package com.example.macbookpro.retrofittesting.network;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String BASE_URL = "http://www.amppil.com/";
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor logging;
    private static OkHttpClient.Builder httpClient;

    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());

    public static Retrofit createService(){
        if(retrofit == null){
            logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(5,TimeUnit.MINUTES)     // set time out
                    .writeTimeout(5, TimeUnit.MINUTES)      // to avoid
                    .readTimeout(5, TimeUnit.MINUTES)       // socket time out exception
                   // .cookieJar(cookieJar)
                    .addInterceptor(logging)  // <-- this is the important line! for log request and response
                    ;

            // add logging as last interceptor
            retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(gsonConverterFactory)
                                .client(httpClient.build())
                                .build();
        }
        return retrofit;
    }
}
