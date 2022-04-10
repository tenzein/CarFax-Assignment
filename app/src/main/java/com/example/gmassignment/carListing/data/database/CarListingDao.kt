package com.example.gmassignment.carListing.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gmassignment.carListing.domain.model.CarData
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@Dao
interface CarListingDao {

    // insert all car list in the table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarList(carList: List<CarData>): Maybe<List<Long>>

    //    fetch all data of photos table from local database
    @Query("Select * From CarData")
    fun getAllCarList(): Maybe<List<CarData>?>

    //    delete all data from photos table
    @Query("DELETE FROM CarData")
    fun deleteAllCars(): Single<Int>
}