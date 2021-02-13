package com.example.myapplication.data

import android.content.Context
import android.util.Log
import com.example.myapplication.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class StockRepository(context: Context) {

    private var stocks = listOf<Stock>()

    init {
        stocks = getStocks(context) ?: emptyList()
        stocks.forEach {
            Log.i("stock_prices", "$it")
        }
    }

    private fun getTextFromResource(context: Context): String {
        return context.resources.openRawResource(R.raw.stock_data)
                .bufferedReader()
                .use { it.readText() }
    }

    private fun getStocks(context: Context): List<Stock>? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(
            List::class.java, Stock::class.java
        )
        val adapter: JsonAdapter<List<Stock>> = moshi.adapter(listType)
        return adapter.fromJson(getTextFromResource(context))
    }

    fun getStockDataForFirm(firmId: Int): Stock? {
        return stocks.firstOrNull { it.firmId == firmId }
    }

}