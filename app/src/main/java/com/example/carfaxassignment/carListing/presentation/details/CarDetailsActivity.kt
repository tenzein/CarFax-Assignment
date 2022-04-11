package com.example.carfaxassignment.carListing.presentation.details

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.carfaxassignment.R
import com.example.carfaxassignment.carListing.domain.model.CarData
import com.example.carfaxassignment.core.ext.Util.getFormattedNumber
import com.example.carfaxassignment.databinding.ActivityCarDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class CarDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarDetailsBinding
    private var dealerPhone: String = ""
    private val CALL_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        populateData()
        binding.btnDetailsCallDealer.setOnClickListener {
            if (dealerPhone != "")
                checkPermission()
            else
                Toast.makeText(this, "No telephone number available", Toast.LENGTH_SHORT).show()

        }
    }


    /**
     * function to populate data
     * */
    private fun populateData() {
        val data = intent.getParcelableExtra<CarData>("data")
        // populate data on UI from intent
        Glide.with(this)
            .load(data?.imageUrl)
            .fitCenter()
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

        binding.txtLocation.text = data.address
        binding.txtExtColor.text = data.exteriorColor
        binding.txtIntColor.text = data.interiorColor
        binding.txtDriverType.text = data.driveType
        binding.txtTransmission.text = data.transmission
        binding.txtBodyStyle.text = data.bodyType
        binding.txtEngine.text = data.engine
        binding.txtFuel.text = data.fuel

        dealerPhone = data.dealerPhone ?: ""
    }

    /**
     * function to check permission for call phone
     * */
    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "Permission to record denied")
            makeRequest()
        } else {
            callDealerPhone(dealerPhone)
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CALL_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Permission has been denied by user")
                    Toast.makeText(
                        this,
                        "Permission Needed to operate this feature!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.i("TAG", "Permission has been granted by user")
                    callDealerPhone(dealerPhone)
                }
            }
        }

    }

    /**
     * function to make a call
     * */
    private fun callDealerPhone(dealerPhone: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$dealerPhone"))
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}