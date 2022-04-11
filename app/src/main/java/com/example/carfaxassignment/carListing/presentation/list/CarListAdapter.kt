package com.example.carfaxassignment.carListing.presentation.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carfaxassignment.R
import com.example.carfaxassignment.carListing.domain.model.CarData
import com.example.carfaxassignment.carListing.presentation.details.CarDetailsActivity
import com.example.carfaxassignment.core.ext.Util.getFormattedNumber
import com.example.carfaxassignment.databinding.CarListingSingleLayoutItemBinding
import java.text.NumberFormat
import java.util.*

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class CarListAdapter(
    var mContext: Context,
    var mCarList: ArrayList<CarData>
) :
    RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    interface OnItemClickListener {
        fun onClick(value: CarData  )
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val mBinding = CarListingSingleLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        val carData: CarData = mCarList[position]
        holder.bind(mContext, carData,onItemClickListener)

    }

    override fun getItemCount(): Int {
        return mCarList.size
    }

    class CarViewHolder(private val itemBinding: CarListingSingleLayoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(mContext: Context, carData: CarData, onItemClickListener: OnItemClickListener?) {

            Glide.with(mContext)
                .load(carData.imageUrl)
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.imgCar)

            itemBinding.txtTitle.text = StringBuilder()
                .append(carData.year)
                .append(" ")
                .append(carData.make)
                .append(" ")
                .append(carData.model)
                .append(" ")
                .append(carData.trim)

            //changing the plain current price to number format eg. 15000 -> 15,000.00
            val formattedPrice =
                NumberFormat.getCurrencyInstance().format(carData.currentPrice)
            itemBinding.txtPrice.text = StringBuilder().append(formattedPrice)

            val formattedMileage = getFormattedNumber(carData.mileage!!)
            itemBinding.txtMileage.text = StringBuilder().append(formattedMileage).append(" mi")

            itemBinding.txtAddress.text = carData.address

            itemBinding.outerLayout.setOnClickListener {
                val intent = Intent(mContext,CarDetailsActivity::class.java)
                intent.putExtra("data", carData)
                mContext.startActivity(intent)
            }
            itemBinding.txtCallDealer.setOnClickListener {
                    onItemClickListener?.onClick(carData)
            }


        }


    }


}