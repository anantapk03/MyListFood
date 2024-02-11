package com.example.mylistfood.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylistfood.data.response.ListFoodResponseItem
import com.example.mylistfood.databinding.ItemListFoodBinding
import com.example.mylistfood.ui.detailfood.DetailFoodActivity
import com.example.mylistfood.ui.detailfood.DetailFoodViewModel

class ListFoodAdapter : ListAdapter<ListFoodResponseItem, ListFoodAdapter.MyViewHolder>(DIFF_CALLBACK) {

    //Holder for bind view item view
    class MyViewHolder (val binding: ItemListFoodBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem : ListFoodResponseItem){
            binding.tvItemName.text = foodItem.name.toString()
            Glide.with(itemView)
                .load(foodItem.image)
                .into(binding.imgFood)
        }
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListFoodResponseItem>(){
            override fun areContentsTheSame(
                oldItem: ListFoodResponseItem,
                newItem: ListFoodResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: ListFoodResponseItem,
                newItem: ListFoodResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        //Intent to detail when Item clicked
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailFoodActivity::class.java)
            intentDetail.putExtra("index", position)

            //Animation
            val optionCompat : ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.binding.imgFood, "imgFood"),
                    Pair(holder.binding.tvItemName, "tvItemName")
                )
            holder.itemView.context.startActivity(intentDetail, optionCompat.toBundle())
        }
    }

}