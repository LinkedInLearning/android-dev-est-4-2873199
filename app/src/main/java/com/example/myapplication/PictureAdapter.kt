package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.Picture
import com.example.myapplication.databinding.PictureItemBinding

class PictureAdapter(private val items: List<Picture>, private val onItemClick: (Picture) -> Unit)
    : RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: PictureItemBinding)
            : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PictureItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = items[position]

        with(holder.binding) {
            pictureImage.load(picture.thumbnailUrl) {
                crossfade(true)
            }
            nameText.text = picture.name
            jobText.text = picture.job
        }

        holder.itemView.setOnClickListener {
            onItemClick(picture)
        }
    }

    override fun getItemCount(): Int = items.size
}
