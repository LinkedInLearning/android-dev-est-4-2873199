package com.example.myapplication

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.Product
import com.example.myapplication.databinding.ProductItemBinding

class ProductAdapter(private val items: List<Product>)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: ProductItemBinding)
            : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = items[position]

        with(holder.binding) {
            productImage.load(product.imageFile) {
                crossfade(1000)
            }
            productNameText.text = product.name
            sizeText.text = sizeText.context.resources.getString(
                R.string.product_size_label,
                product.size
            )
            priceText.text = NumberFormat.getCurrencyInstance().format(product.price)
        }
    }

    override fun getItemCount(): Int = items.size
}