package com.example.mylistfood.data.retrofit

import com.example.mylistfood.data.response.ListFoodResponseItem
import retrofit2.http.GET

interface ApiService {
    //Return Response API, List from ListFoodResponseItem
    @GET("foods")
    suspend fun getListFoods(): List<ListFoodResponseItem>
}