package com.example.mylistfood.ui.detailfood

import androidx.lifecycle.ViewModel
import com.example.mylistfood.repository.FoodRepository

class DetailFoodViewModel (private val repository: FoodRepository) : ViewModel() {

    fun getDetailFood(index: Int) = repository.getDetailFood(index)

}