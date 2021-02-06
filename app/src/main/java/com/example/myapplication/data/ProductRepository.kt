package com.example.myapplication.data

import android.content.Context

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

}