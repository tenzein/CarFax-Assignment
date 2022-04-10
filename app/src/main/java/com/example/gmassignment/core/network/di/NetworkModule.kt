package com.example.gmassignment.core.network.di

import androidx.viewbinding.BuildConfig
import com.example.gmassignment.carListing.data.api.CarListingApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    /**
     * provide carListingApiService for fetching data from remote server
     * @param retrofit
     * */
    @Provides
    @Singleton
    fun provideCarListingApiService(retrofit: Retrofit): CarListingApi =
        retrofit.create(CarListingApi::class.java)

    /**
     * provider for building Retrofit Builder with httpClient, converter factory
     * @param httpClient OKHttpClient Builder
     * @param convertFactory GsonConverterFactory
     * */
    @Singleton
    @Provides
    fun provideGsonRetrofit(
        httpClient: OkHttpClient.Builder,
        convertFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://carfax-for-consumers.firebaseio.com/")
        .client(httpClient.build())
        .addConverterFactory(convertFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    /**
     * provider for building OkHttp with interceptor
     * @param httpLoggingInterceptor HttpLoggingInterceptor
     * */
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        httpClient.retryOnConnectionFailure(true)
        return httpClient

    }

    /**
     * provider for building HttpLoggingInterceptor
     * */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    /**
     * provider for building JacksonConverterFactory
     * */
    @Provides
    @Singleton
    fun provideJacksonConverterFactory(): JacksonConverterFactory =
        JacksonConverterFactory.create()

    /**
     * provider for building GsonConverterFactory
     * */
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()
}