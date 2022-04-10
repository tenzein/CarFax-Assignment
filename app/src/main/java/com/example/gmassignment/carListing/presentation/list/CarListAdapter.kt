package com.example.gmassignment.carListing.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.databinding.CarListingSingleLayoutItemBinding
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class CarListAdapter(var mContext: Context,var mCarList: ArrayList<CarData>) :
    RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val mBinding = CarListingSingleLayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        val carData: CarData = mCarList[position]
        holder.bind(mContext,carData)
    }

    override fun getItemCount(): Int {
        return  mCarList.size
    }

    class CarViewHolder(private val itemBinding: CarListingSingleLayoutItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind(mContext: Context,carData: CarData){

            Glide.with(mContext)
                .load(carData.imageUrl)
                .into(itemBinding.imgCar)

            itemBinding.txtTitle.text = StringBuilder().append(carData.year).append(carData.make)
                .append(carData.model)
                .append(carData.trim)

            //changing the plain current price to number format eg. 15000 -> 15,000.00
            val formattedPrice = NumberFormat.getCurrencyInstance(Locale("US","en")).format(carData.currentPrice)
            itemBinding.txtPrice.text = StringBuilder().append("$").append(formattedPrice)

            val formattedMileage = NumberFormat.getCurrencyInstance().format(carData.mileage)
            itemBinding.txtMileage.text = formattedMileage

        }
    }
}