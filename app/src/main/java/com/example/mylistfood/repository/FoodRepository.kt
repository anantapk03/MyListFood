package com.example.mylistfood.repository

import androidx.lifecycle.liveData
import com.example.mylistfood.data.ResultState
import com.example.mylistfood.data.response.ListFoodResponseItem
import com.example.mylistfood.data.retrofit.ApiService
import retrofit2.HttpException

class FoodRepository private constructor(
    private val apiService: ApiService
){


    fun getFoods() = liveData<ResultState<List<ListFoodResponseItem>>> {
        emit(ResultState.Loading)
        try {
            //Get List Food From API
            val successGetListFoods = apiService.getListFoods()
            emit(ResultState.Success(successGetListFoods))
        } catch (e: HttpException){
            emit(ResultState.Error("Failed Request"))
        }
    }

    fun getDetailFood(index: Int) = liveData<ResultState<ListFoodResponseItem>> {
        emit(ResultState.Loading)
        try {
            val getListFood = apiService.getListFoods()
            //Get data by index af
            val getDataItem = getListFood.get(index)
            emit(ResultState.Success(getDataItem))
        } catch (e: HttpException){
            emit(ResultState.Error("Failed Get Data"))
        }
    }

    //Get instance object
    companion object{
        @Volatile
        private var instance : FoodRepository? = null

        fun getInstance(
            apiService: ApiService
        ) : FoodRepository =
            instance ?: synchronized(this){
                instance ?: FoodRepository(apiService)
            }.also { instance = it }
    }
}