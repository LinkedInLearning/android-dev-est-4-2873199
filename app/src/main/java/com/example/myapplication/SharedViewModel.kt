package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myapplication.data.Product
import com.example.myapplication.data.ProductRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    var productRepository: ProductRepository = ProductRepository()

    val products: LiveData<List<Product>> = liveData {
        val data = productRepository.getProducts()
        emit(data)
    }
}