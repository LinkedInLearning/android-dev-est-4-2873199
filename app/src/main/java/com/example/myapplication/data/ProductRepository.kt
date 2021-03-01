package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://2873199.youcanlearnit.net/"

class ProductRepository {

    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun getTextFromResource(context: Context): String {
        return context.resources.openRawResource(R.raw.olive_oils_data)
                .bufferedReader()
                .use { it.readText() }
    }

    fun getProducts(context: Context): List<Product>? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(
            List::class.java, Product::class.java
        )
        val adapter: JsonAdapter<List<Product>> = moshi.adapter(listType)
        return adapter.fromJson(getTextFromResource(context))
    }

}