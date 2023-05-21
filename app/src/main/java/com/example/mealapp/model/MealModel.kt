package com.example.mealapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealModel(
    val id: Int,
    val name: String,
    val categories: List<Int>,
    val imageUrl: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val duration: Int,
    val complexity: String,
    val affordability: String,
    val isGlutenFree: Boolean,
    val isLactoseFree: Boolean,
    val isVegan: Boolean,
    val isVegetarian: Boolean
):Parcelable

//internal enum class Complexity {
//    simple, challenging, hard
//}
//
//internal enum class Affordability {
//    affordable, pricey, luxurious
//}