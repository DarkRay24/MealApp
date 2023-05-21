package com.example.mealapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mealapp.model.MealModel

class MainViewModel(app: Application,) : AndroidViewModel(app) {

    // Title
    var appTitle = MutableLiveData<String>()
    init {
        appTitle.value = "Categories"
    }
    fun updateTitle(newTitle : String){
        appTitle.value = newTitle
    }


    // Favorite Dishes
    val favDishList = MutableLiveData<ArrayList<MealModel>>()
    fun addFavDish(dish : MealModel){
        favDishList.value?.add(dish)
    }


    // Filters
    var isGlutenFree = MutableLiveData<Boolean>()
    init {
        isGlutenFree.value = false
    }
    var isLactoseFree = MutableLiveData<Boolean>()
    init {
        isLactoseFree.value = false
    }var isVegetarian = MutableLiveData<Boolean>()
    init {
        isVegetarian.value = false
    }var isVegan = MutableLiveData<Boolean>()
    init {
        isVegan.value = false
    }


    fun setFilterTrue(filter : MutableLiveData<Boolean>){
        filter.value = true
    }

    fun setFilterFalse(filter : MutableLiveData<Boolean>){
        filter.value = false
    }
}