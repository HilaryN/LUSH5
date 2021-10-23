package com.example.lush5

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("launches")
    fun getLaunches(): Call<List<Launch?>?>?
}