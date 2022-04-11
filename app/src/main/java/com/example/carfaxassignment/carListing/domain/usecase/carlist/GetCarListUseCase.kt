package com.example.carfaxassignment.carListing.domain.usecase.carlist

import com.example.carfaxassignment.carListing.data.mapper.carlist.CarListToDbMapper
import com.example.carfaxassignment.carListing.domain.model.CarData
import com.example.carfaxassignment.carListing.domain.repository.CarListRepository
import com.example.carfaxassignment.carListing.domain.usecase.carlist.GetCarListUseCase.Result.Success
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class GetCarListUseCase @Inject constructor(
    private val carListRepository: CarListRepository
) {

    /**
     * Seal class to handle the state of the background call for fetching
     * data to update the UI
     * */
    sealed class Result {
        object Loading : Result()
        data class Success(val response: List<CarData>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }


    /**
     * function to observe the data from sources checking network availability
     * @param hasNetwork boolean value of network availability
     * */
    fun getCarList(hasNetwork: Boolean): Observable<Result> {
        return if (!hasNetwork) {
            return carListRepository.getCarListFromDb()
                .toObservable()
                .map {
                    Success(it) as Result
                }
                .onErrorReturn { Result.Failure(it) }
                .startWith(Result.Loading)
        } else {
            carListRepository.deleteAllCarList() // delete all data to update new data
            carListRepository.getCarList().toObservable().map {
                val data = CarListToDbMapper().reverseMap(it.listings)
                Success(data) as Result
            }
                .onErrorReturn { Result.Failure(it) }
                .startWith(Result.Loading)
        }
    }

}