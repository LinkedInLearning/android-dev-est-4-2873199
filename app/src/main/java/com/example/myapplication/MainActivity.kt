package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

const val LOG_TAG = "RandomPics"

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pickRandomImagesButton.setOnClickListener {
            viewModel.showRandomImage()
        }

        viewModel.pictures.observe(this) {
            val pictureAdapter = PictureAdapter(it)
            binding.picturesList.adapter = pictureAdapter
            Log.i(LOG_TAG, "received list of pictures: $it")
        }
    }
}
