package com.example.gmassignment.carListing.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gmassignment.carListing.domain.model.CarData
import com.example.gmassignment.carListing.domain.usecase.carlist.GetCarListUseCase
import com.example.gmassignment.carListing.domain.usecase.carlist.InsertCarListUseCase
import com.example.gmassignment.core.ext.addTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val insertCarListUseCase: InsertCarListUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()
    val carList = MutableLiveData<List<CarData>>()

    /**
     * function to get all the photos
     * @param hasNetwork boolean value whether network is available or not
     * */
    fun bound(hasNetwork: Boolean) {
        getCarListUseCase.getCarList(hasNetwork)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)

    }

    /**
     * function to handle the result of the GetPhotosUseCase
     * @param result seal class of Result to handle the state
     * */
    private fun handleResult(result: GetCarListUseCase.Result) {
        when (result) {
            is GetCarListUseCase.Result.Loading -> progressVisible.value = true
            is GetCarListUseCase.Result.Success -> {
                insert(result.response)
                carList.value = result.response
                progressVisible.value = false
            }
            is GetCarListUseCase.Result.Failure -> "error"
        }
    }

    /**
     * function to insert all photos data to local database
     * @param data list of photosData object
     * */
    @SuppressLint("CheckResult")
    private fun insert(data: List<CarData>) {
        insertCarListUseCase.insertCarList(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    /**
     * function to clear disposables
     * */
    fun unbound() {
        disposables.clear()
    }

}