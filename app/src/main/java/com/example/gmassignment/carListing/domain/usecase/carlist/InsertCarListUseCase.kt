package com.example.gmassignment.carListing.domain.usecase.carlist

import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.carListing.domain.repository.CarListRepository
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class InsertCarListUseCase @Inject constructor(
    private val carListRepository: CarListRepository
) {
    fun insertCarList(data: List<CarData>): Maybe<List<Long>> {
        return carListRepository.insertAllCarList(data)
    }
}