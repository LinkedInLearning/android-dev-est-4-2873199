package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        }

        viewModel?.products?.observe(viewLifecycleOwner, { products ->
            val productNames = StringBuilder()
            products.forEach {
                productNames.appendLine(it.name)
            }

            binding.cartContentText.text = productNames.toString()
        })

        binding.addQuantityButton.setOnClickListener { viewModel?.increaseQuantity() }
        binding.removeQuantityButton.setOnClickListener { viewModel?.decreaseQuantity() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}