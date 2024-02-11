package com.example.mylistfood.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mylistfood.R
import com.example.mylistfood.data.ResultState
import com.example.mylistfood.data.response.ListFoodResponseItem
import com.example.mylistfood.databinding.ActivityMainBinding
import com.example.mylistfood.ui.ViewModelFactory

class MainActivity : AppCompatActivity() {

    //Initiaton binding and ViewModel
    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initiation Recyclerview (Showing RV Linear)
        val layoutManager = LinearLayoutManager(this)
        binding.rvListFood.layoutManager = layoutManager

        getListFoods()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun setListFood(dataItem : List<ListFoodResponseItem?>?){
        val adapter = ListFoodAdapter()
        adapter.submitList(dataItem)
        binding.rvListFood.adapter = adapter
    }

    private fun getListFoods(){
        viewModel.getListFoods().observe(this){result ->
            if (result != null){
                when(result){
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Success -> {
                        setListFood(result.data)
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

    //Initiation optionmenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Action when option item menu selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //Will show action list linear
            R.id.action_list -> {
                binding.rvListFood.layoutManager = LinearLayoutManager(this)
            }

            //Will show view list tobe grid with 2 column
            R.id.action_grid -> {
                binding.rvListFood.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}