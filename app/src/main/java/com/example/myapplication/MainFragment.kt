package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.Picture
import com.example.myapplication.databinding.FragmentMainBinding

const val LOG_TAG = "RandomPics"

class MainFragment : Fragment() {

    private var viewModel: SharedViewModel? = null
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val onItemClick: (Picture) -> Unit = { picture ->
        viewModel?.selectedPicture?.value = picture
        TODO("handle navigation")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        }

        binding.pickRandomImagesButton.setOnClickListener {
            viewModel?.showRandomImage()
        }

        viewModel?.pictures?.observe(viewLifecycleOwner, {
            val pictureAdapter = PictureAdapter(it, onItemClick)
            binding.picturesList.adapter = pictureAdapter
            Log.i(LOG_TAG, "received list of pictures: $it")
        })
    }
}
