package com.example.gmassignment.carListing.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gmassignment.R
import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.core.ext.Util.getFormattedNumber
import com.example.gmassignment.databinding.ActivityCarDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class CarDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<CarData>("data")

        // populate data on UI from intent

        Glide.with(this)
            .load(data?.imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.detailsImageView)

        binding.txtTitle.text = StringBuilder()
            .append(data?.year)
            .append(" ")
            .append(data?.make)
            .append(" ")
            .append(data?.model)
            .append(" ")
            .append(data?.trim)

        //changing the plain current price to number format eg. 15000 -> 15,000.00
        val formattedPrice =
            NumberFormat.getCurrencyInstance().format(data?.currentPrice)
        binding.txtPrice.text = StringBuilder().append(formattedPrice)

        val formattedMileage = getFormattedNumber(data?.mileage!!)
        binding.txtMileage.text = StringBuilder().append(formattedMileage).append(" mi")
    }
}