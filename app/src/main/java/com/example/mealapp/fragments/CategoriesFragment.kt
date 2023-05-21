package com.example.mealapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mealapp.MainActivity
import com.example.mealapp.R
import com.example.mealapp.adapter.CategoriesAdapter
import com.example.mealapp.databinding.FragmentCategoriesBinding
import com.example.mealapp.model.CategoryModel
import com.example.mealapp.model.MealModel
import com.example.mealapp.viewmodel.MainViewModel

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private var _binding : FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoriesAdapter : CategoriesAdapter
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).mainViewModel


        mainViewModel.updateTitle("Categories")
        setUpCategoryRecyclerView()
    }

    private fun setUpCategoryRecyclerView() {
        val categoryList = createCategoryData()
        categoriesAdapter = CategoriesAdapter(categoryList, mainViewModel)

        binding.rvCategories.apply{
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL,
            )
            setHasFixedSize(true)
            adapter = categoriesAdapter

        }
    }


    private fun createCategoryData(): ArrayList<CategoryModel> {
        val categoryList : ArrayList<CategoryModel> = ArrayList()

        val category1 = CategoryModel(1, "Italian", R.drawable.gradient1)
        val category2 = CategoryModel(2, "Quick & Easy", R.drawable.gradient2)
        val category3 = CategoryModel(3, "Hamburgers", R.drawable.gradient3)
        val category4 = CategoryModel(4, "German", R.drawable.gradient4)
        val category5 = CategoryModel(5, "Light & Lovely", R.drawable.gradient5)
        val category6 = CategoryModel(6, "Exotic", R.drawable.gradient6)
        val category7 = CategoryModel(7, "Breakfast", R.drawable.gradient8)
        val category8 = CategoryModel(8, "Asian", R.drawable.gradient7)
        val category9 = CategoryModel(9, "French", R.drawable.gradient9)
        val category10 = CategoryModel(10, "Summer", R.drawable.gradient10)

        categoryList.add(category1); categoryList.add(category2); categoryList.add(category3);
        categoryList.add(category4); categoryList.add(category5); categoryList.add(category6);
        categoryList.add(category7); categoryList.add(category8); categoryList.add(category9);
        categoryList.add(category10);

        return categoryList
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}