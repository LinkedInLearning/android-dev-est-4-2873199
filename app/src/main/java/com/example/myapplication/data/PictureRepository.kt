package com.example.myapplication.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://2873199.youcanlearnit.net/"

class PictureRepository {

    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val pictureApi: PictureApi by lazy {
        retrofit.create(PictureApi::class.java)
    }

    suspend fun getProducts(page: Int): List<Picture> {
        val response = pictureApi.getPictures(page)
        return if (response.isSuccessful)
            response.body() ?: emptyList()
        else
            emptyList()
    }
}
