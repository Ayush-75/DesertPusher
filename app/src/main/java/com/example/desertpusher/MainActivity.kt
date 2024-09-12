package com.example.desertpusher

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import com.example.desertpusher.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity(), LifecycleObserver {

    val TAG = "MainActivity"

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
        Timber.tag(TAG).d("onCreate: gets triggered")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dessertButton.setOnClickListener {
            onDessertClicked()
        }

        binding.revenue = revenue
        binding.amountSold = dessertsSold

        binding.dessertButton.setImageResource(currentDessert.imageId)
    }

    private fun onDessertClicked() {
        revenue += currentDessert.price
        dessertsSold++

        binding.revenue = revenue
        binding.amountSold = dessertsSold

        showCurrentDessert()
    }

    private fun showCurrentDessert() {
        var newDessert = allDesserts[0]
        for (dessert in allDesserts){
            if (dessertsSold >= dessert.startProductionAmount){
                newDessert = dessert
            }
            else break
        }

        if (newDessert != currentDessert){
            currentDessert = newDessert
            binding.dessertButton.setImageResource(newDessert.imageId)
        }
    }

    private fun onShare(){
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText(getString(R.string.share_text,dessertsSold,revenue))
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        }catch (ex:ActivityNotFoundException){
            Toast.makeText(this, getString(R.string.sharing_not_available), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.shareMenuButton -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).d("onStart: gets triggered")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG).d("onStop: gets triggered")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(TAG).d("onPause: gets triggered")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.tag(TAG).d("onRestart: gets triggered")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).d("onDestroy: gets triggered")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG).d("onResume: gets triggered")
    }
}