package com.example.myapplication.data

data class Picture(
        val id: Int,
        val author: String,
        val url: String
) {
        val imageUrl: String
                get() = "https://picsum.photos/id/${id}/300.webp"
}
