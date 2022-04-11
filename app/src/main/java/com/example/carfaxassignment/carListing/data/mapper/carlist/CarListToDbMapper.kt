package com.example.carfaxassignment.carListing.data.mapper.carlist

import com.example.carfaxassignment.carListing.data.response.Listings
import com.example.carfaxassignment.carListing.domain.model.CarData
import com.example.carfaxassignment.core.database.Mapper

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class CarListToDbMapper : Mapper<CarData, Listings>() {
    override fun map(value: CarData): Listings {
        throw UnsupportedOperationException()
    }

    override fun reverseMap(value: Listings): CarData {
        return CarData(
            value.id,
            value.dealer.phone,
            value.vin,
            value.mileage,
            value.currentPrice,
            value.exteriorColor,
            value.interiorColor,
            value.engine +" ${value.displacement}",
            value.drivetype,
            value.transmission,
            value.bodytype,
            value.images.firstPhoto.large,
            value.year,
            value.make,
            value.model,
            value.dealer.address,
            value.trim,
            value.fuel
            )

    }
}