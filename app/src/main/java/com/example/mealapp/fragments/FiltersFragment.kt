package com.example.mealapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealapp.MainActivity
import com.example.mealapp.R
import com.example.mealapp.databinding.FragmentFiltersBinding
import com.example.mealapp.viewmodel.MainViewModel

class FiltersFragment : Fragment(R.layout.fragment_filters) {

    private var _binding : FragmentFiltersBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)

        mainViewModel = (activity as MainActivity).mainViewModel
        mainViewModel.appTitle.value = "Your Filters"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchGluten.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainViewModel.setFilterTrue(mainViewModel.isGlutenFree)

            } else {
                mainViewModel.setFilterFalse(mainViewModel.isGlutenFree)
            }
        }
        binding.switchLactose.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainViewModel.setFilterTrue(mainViewModel.isLactoseFree)

            } else {
                mainViewModel.setFilterFalse(mainViewModel.isLactoseFree)
            }
        }
        binding.switchVegan.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainViewModel.setFilterTrue(mainViewModel.isVegetarian)

            } else {
                mainViewModel.setFilterFalse(mainViewModel.isVegetarian)
            }
        }
        binding.switchVegan.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainViewModel.setFilterTrue(mainViewModel.isVegan)

            } else {
                mainViewModel.setFilterFalse(mainViewModel.isVegan)
            }
        }
    }







    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}