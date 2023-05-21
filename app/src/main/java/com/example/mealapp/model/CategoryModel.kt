package com.example.mealapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(val id: Int, val name: String, val color: Int):Parcelable
