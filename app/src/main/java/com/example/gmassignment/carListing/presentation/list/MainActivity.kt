package com.example.gmassignment.carListing.presentation.list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.carListing.presentation.CarListViewModel
import com.example.gmassignment.core.network.NetworkConnection
import com.example.gmassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    private val carListViewModel: CarListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var mCarList = ArrayList<CarData>()
    private lateinit var mCarListAdapter: CarListAdapter


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
        mCarListAdapter = CarListAdapter(this,mCarList)
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

}