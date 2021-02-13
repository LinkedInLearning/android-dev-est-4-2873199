package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.data.Stock
import com.example.myapplication.databinding.FragmentStocksBinding

class StocksFragment : Fragment() {

    private var _binding: FragmentStocksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStocksBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.stockInfo.observe(viewLifecycleOwner, {
            updateStockInfo(it)
        })

        binding.firm1Btn.setOnClickListener {
            viewModel.getStockData(firmId = 1)
        }

        binding.firm2Btn.setOnClickListener {
            viewModel.getStockData(firmId = 2)
        }

        binding.firm3Btn.setOnClickListener {
            viewModel.getStockData(firmId = 3)
        }

        return view
    }

    private fun updateStockInfo(stock: Stock) {
        binding.firmLabelText.text = when (stock.firmId) {
            1 -> getString(R.string.firm_1_btn_label)
            2 -> getString(R.string.firm_2_btn_label)
            3 -> getString(R.string.firm_3_btn_label)
            else -> ""
        }
        binding.stockInfoText.text = getString(
            R.string.stock_info,
            stock.open,
            stock.close,
            stock.change
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}