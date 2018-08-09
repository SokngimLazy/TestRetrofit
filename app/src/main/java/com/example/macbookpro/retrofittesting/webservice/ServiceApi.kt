package com.example.macbookpro.retrofittesting.webservice

import com.example.macbookpro.retrofittesting.model.LogoutRequestModel
import com.example.macbookpro.retrofittesting.model.LogoutResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    @POST("OTO/Api")
    fun logout(@Body body: LogoutRequestModel): Observable<LogoutResponse>
}