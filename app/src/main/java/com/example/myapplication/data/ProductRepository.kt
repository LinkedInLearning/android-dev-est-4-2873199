package com.example.myapplication.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProductRepository {

    fun getTextFromResource(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
                .bufferedReader()
                .use { it.readText() }
    }

    fun getTextFromAsset(context: Context, fileName: String): String {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    fun getProducts(context: Context, fileName: String): List<Product>? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(
            List::class.java, Product::class.java
        )
        val adapter: JsonAdapter<List<Product>> = moshi.adapter(listType)
        return adapter.fromJson(getTextFromAsset(context, fileName))
    }

}