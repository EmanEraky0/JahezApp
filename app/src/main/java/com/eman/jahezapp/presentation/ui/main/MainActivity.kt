package com.eman.jahezapp.presentation.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eman.jahezapp.domain.model.Restaurant
import com.eman.jahezapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import jahezapp.R
import jahezapp.databinding.ActivityMainBinding
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isLoading: Boolean = false
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainRestaurantAdapter

    //list for holding data
    lateinit var list: ArrayList<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        list = ArrayList()
        adapter = MainRestaurantAdapter(arrayListOf())
        binding.mainAdapter = adapter

        mainViewModel.getRestaurantResponse()

        setObserveRestaurant()
    }


    private fun setObserveRestaurant() {
        mainViewModel.restaurant.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { restaurant ->

                        renderList(restaurant as ArrayList<Restaurant>)
                    }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        }
    }


    private fun renderList(lRestaurant: ArrayList<Restaurant>) {
        list.addAll(lRestaurant)
        adapter.addData(list)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_lang -> {
                changeLangApp()
                true
            }
            R.id.action_sort -> {
                val sortedList = list.sortedWith(compareBy { !it.hasOffer })

                adapter.addData(sortedList)
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeLangApp() {
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
        val refresh = Intent(this, MainActivity::class.java)
        finish()
        startActivity(refresh)
    }
}