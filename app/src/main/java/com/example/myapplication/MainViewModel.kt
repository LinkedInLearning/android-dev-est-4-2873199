package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.Picture
import com.example.myapplication.data.PictureRepository
import kotlin.random.Random

class MainViewModel : ViewModel() {

    var pictureRepository: PictureRepository = PictureRepository()
    private val randomPageNumber: MutableLiveData<Int> = MutableLiveData()

    val pictures: LiveData<List<Picture>> = randomPageNumber.switchMap {
        liveData {
            val data = pictureRepository.getProducts(page = it)
            Log.i(LOG_TAG, "the data: $data")
            emit(data)
        }
    }

    fun showRandomImage() {
        randomPageNumber.value = Random.nextInt(0, 3)
    }
}
