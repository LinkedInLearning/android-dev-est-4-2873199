package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.Picture
import com.example.myapplication.databinding.PictureItemBinding

class PictureAdapter(private val items: List<Picture>)
    : RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: PictureItemBinding)
            : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO()
    }

    override fun getItemCount(): Int = TODO()
}
