package com.example.desertpusher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import com.example.desertpusher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LifecycleObserver {


    private var revenue = 0
    private var dessertsSold = 0

    private lateinit var binding: ActivityMainBinding

    data class Dessert(val imageId: Int, val price: Int, val startProductionAmount: Int)

    private val allDesserts = listOf<Dessert>(
        Dessert(R.drawable.cupcake, 5, 0),
        Dessert(R.drawable.donut, 10, 5),
        Dessert(R.drawable.eclair, 15, 20),
        Dessert(R.drawable.froyo, 30, 50),
        Dessert(R.drawable.gingerbread, 50, 100),
        Dessert(R.drawable.honeycomb, 100, 200),
        Dessert(R.drawable.icecreamsandwich, 500, 500),
        Dessert(R.drawable.jellybean, 1000, 1000),
        Dessert(R.drawable.kitkat, 2000, 2000),
        Dessert(R.drawable.lollipop, 3000, 4000),
        Dessert(R.drawable.marshmallow, 4000, 8000),
        Dessert(R.drawable.nougat, 5000, 16000),
        Dessert(R.drawable.oreo, 6000, 20000)
    )

    private var currentDessert = allDesserts[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dessertButton.setOnClickListener {
            onDessertClicked()
        }
    }

    private fun onDessertClicked() {

    }
}