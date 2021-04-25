package com.example.myapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PictureApi {

    @GET("/robots/{page}/robots.json")
    suspend fun getPictures(@Path("page") page: Int): Response<List<Picture>>
}
