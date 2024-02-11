package com.example.mylistfood.ui.detailfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.mylistfood.R
import com.example.mylistfood.data.ResultState
import com.example.mylistfood.data.response.ListFoodResponseItem
import com.example.mylistfood.databinding.ActivityDetailFoodBinding
import com.example.mylistfood.ui.ViewModelFactory

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailFoodBinding

    private val viewModel by viewModels<DetailFoodViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var index = intent.getIntExtra("index", -1)

        getDetailFood(index)

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setDetailFood(dataItem : ListFoodResponseItem){
        Glide.with(this)
            .load(dataItem.image)
            .into(binding.imgFood)

        Glide.with(this)
            .load(dataItem.image)
            .into(binding.imgBackground)

        binding.tvNameFood.text = dataItem.name
        binding.tvDescFood.text = dataItem.desc
    }

    private fun getDetailFood(index: Int){
        viewModel.getDetailFood(index).observe(this){result ->
            if(result != null){
                when(result){
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Success -> {
                        setDetailFood(result.data)
                        showLoading(false)
                    }
                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }

        }
    }
}