package com.example.gmassignment.carListing.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gmassignment.R
import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.databinding.CarListingSingleLayoutItemBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

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

            itemBinding.txtCallDealer.setOnClickListener {
                    onItemClickListener?.onClick(carData)
            }

        }

        /**
         * function to convert mileage to k mi
         * */
        private fun getFormattedNumber(count: Long): String {
            if (count < 1000) return "" + count
            val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
            return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
        }
    }


}