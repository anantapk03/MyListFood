package com.example.mylistfood.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mylistfood.di.Injection
import com.example.mylistfood.repository.FoodRepository
import com.example.mylistfood.ui.detailfood.DetailFoodViewModel
import com.example.mylistfood.ui.main.MainViewModel

class ViewModelFactory (private val repository: FoodRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java)-> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailFoodViewModel::class.java)->{
                DetailFoodViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown Viewmodel Class: "+ modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory{
            if(INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}