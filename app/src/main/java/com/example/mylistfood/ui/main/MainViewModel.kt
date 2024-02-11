package com.example.mylistfood.ui.main

import androidx.lifecycle.ViewModel
import com.example.mylistfood.repository.FoodRepository

class MainViewModel (private val repository: FoodRepository):ViewModel() {

    fun getListFoods() = repository.getFoods()

}