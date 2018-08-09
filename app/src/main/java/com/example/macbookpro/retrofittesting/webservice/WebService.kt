package com.example.macbookpro.retrofittesting.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebService {

    private val baseLiveUrl: String = "https://www.amppil.com/"
    private val mContentType: String = "Content-Type"
    private val mContentTypeValue: String = "application/json"

    private val mAuthorizationType: String = "Authorization"
    private val mAuthorizationValue: String = "Bearer "

    private val mUserToken: String = "token"

    fun requestLive(): ServiceApi {
        val mOkHttpClientBuilder = OkHttpClient().newBuilder()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor { chain ->
                    var mRequest = chain.request()
                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .build()

                    chain.proceed(mRequest)
                }
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(ServiceApi::class.java)
    }

    fun requestLive(authorization: String): ServiceApi {
        val mOkHttpClientBuilder = OkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(ServiceApi::class.java)
    }

    fun requestLive(authorization: String, token: String): ServiceApi {
        val mOkHttpClientBuilder = OkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .header(mUserToken, token)
                            .build()
                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(ServiceApi::class.java)
    }
}