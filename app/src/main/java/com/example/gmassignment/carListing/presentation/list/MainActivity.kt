package com.example.gmassignment.carListing.presentation.list

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.carListing.presentation.CarListViewModel
import com.example.gmassignment.core.network.NetworkConnection
import com.example.gmassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CarListAdapter.OnItemClickListener {
    private val disposables = CompositeDisposable()

    private val carListViewModel: CarListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var mCarList = ArrayList<CarData>()
    private lateinit var mCarListAdapter: CarListAdapter

    private var dealerPhone: String = ""
    private val CALL_REQUEST_CODE = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter() // initialize the adapter
        carListViewModel.bound(NetworkConnection.checkNetwork())
        observeData() //observe the network data response
    }

    /**
     * initialize the listview adapter
     * set adapter to listview
     * */
    private fun initAdapter() {
        mCarListAdapter = CarListAdapter(this, mCarList)
        mCarListAdapter.setOnClickListener(this)
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.carRecyclerview.layoutManager = mLayoutManager
        binding.carRecyclerview.adapter = mCarListAdapter

    }

    /**
     * function to observe the response data from local/remote server
     * notify the data set changes to listview adapter
     * */
    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        carListViewModel.carList.observe(this) {
            mCarList.addAll(it)
            mCarListAdapter.notifyDataSetChanged()

        }

        carListViewModel.progressVisible.observe(this) {
            binding.progressBar.isVisible = it

        }
    }

    override fun onPause() {
        disposables.clear()
        super.onPause()
    }

    override fun onDestroy() {
        carListViewModel.unbound()
        super.onDestroy()
    }

    override fun onClick(value: CarData) {
        if (value.dealerPhone != "") {
            dealerPhone = value.dealerPhone!!
            checkPermission()
        } else
            Toast.makeText(this, "No telephone number available", Toast.LENGTH_SHORT).show()
    }


    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "Permission to record denied")
            makeRequest()
        }else{
            callDealerPhone(dealerPhone)
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_REQUEST_CODE)
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

    private fun callDealerPhone(dealerPhone: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$dealerPhone"))
        startActivity(intent)
    }
}