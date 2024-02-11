package com.example.mylistfood.data.response

import com.google.gson.annotations.SerializedName

data class ListFoodResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)
