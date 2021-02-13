package com.example.myapplication.data

import com.squareup.moshi.Json

data class Stock(
    @Json(name = "firm_id") val firmId: Int,
    val open: Double,
    val close: Double,
    val change: Double
)