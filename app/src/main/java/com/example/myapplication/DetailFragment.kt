package com.example.myapplication

import android.icu.text.NumberFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var viewModel: SharedViewModel? = null
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        }

        viewModel?.products?.observe(viewLifecycleOwner, { products ->
           with(products[0]) {
               binding.productImage.load(imageFile)
               binding.productNameText.text = name
               binding.descriptionText.text = description
               binding.sizeText.text = getString(R.string.product_size_label, size)
               binding.priceText.text = NumberFormat.getCurrencyInstance().format(price)
           }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}