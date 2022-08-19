package com.joy.apitrial.di

import com.joy.apitrial.api.ApiConsts
import com.joy.apitrial.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): ProductApi {
        return builder
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit.Builder{
        return Retrofit
            .Builder()
            .baseUrl(ApiConsts.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}