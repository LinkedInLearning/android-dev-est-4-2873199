package com.example.myapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {
    @GET("/v2/list")
    suspend fun getPictures(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 5
    ): Response<List<Picture>>
}
