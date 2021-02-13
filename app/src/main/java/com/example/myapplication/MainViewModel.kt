package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.Stock
import com.example.myapplication.data.StockRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _stockInfo: MutableLiveData<Stock> = MutableLiveData()
    val stockInfo: LiveData<Stock> = _stockInfo

    var stockRepository: StockRepository = StockRepository(app)

    fun getStockData(firmId: Int) {
        val stock = stockRepository.getStockDataForFirm(firmId)
        stock?.let { _stockInfo.value = it }
    }

}