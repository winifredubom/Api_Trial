package com.joy.apitrial.api

import com.joy.apitrial.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET(ApiConsts.END_POINTS)
    suspend fun getProduct(): ProductResponse
}