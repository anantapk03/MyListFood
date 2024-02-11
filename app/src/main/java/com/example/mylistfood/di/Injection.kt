package com.example.mylistfood.di

import android.content.Context
import com.example.mylistfood.data.retrofit.ApiConfig
import com.example.mylistfood.repository.FoodRepository

object Injection {
    fun provideRepository(context: Context):FoodRepository{
        val apiService = ApiConfig.getApiService()
        return FoodRepository.getInstance(apiService)
    }
}