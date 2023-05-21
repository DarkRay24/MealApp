package com.example.mealapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.R
import com.example.mealapp.databinding.CategoriesLayoutBinding
import com.example.mealapp.fragments.CategoriesFragmentDirections
import com.example.mealapp.model.CategoryModel
import com.example.mealapp.viewmodel.MainViewModel

class CategoriesAdapter(private val categoryList : ArrayList<CategoryModel>, private val mainViewModel: MainViewModel)
    : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    class CategoriesViewHolder(val itemBinding: CategoriesLayoutBinding)
        : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoriesLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.itemBinding.tvCategoriesName.text = categoryList[position].name

//        val color = Color.parseColor("#FF".plus(categoryList[position].color))
        holder.itemBinding.tvCategoriesName.setBackgroundResource(categoryList[position].color)



        holder.itemView.setOnClickListener {
            val direction = CategoriesFragmentDirections
                .actionCategoriesFragmentToMealsFragment(categoryList[position])
            it.findNavController().navigate(direction)


        }
    }
}