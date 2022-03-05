package com.eman.jahezapp.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eman.jahezapp.domain.model.Restaurant
import jahezapp.R
import jahezapp.databinding.ItemRestaurantBinding

class MainRestaurantAdapter(
    private var restaurant: ArrayList<Restaurant>
) :
    RecyclerView.Adapter<MainRestaurantAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataBindingUtil.inflate<ItemRestaurantBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_restaurant,
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = restaurant.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.restaurantApi = restaurant[position]
    }

    class DataViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root)

    fun addData(list: ArrayList<Restaurant>) {
        restaurant=(list)
    }


}