package com.jiwondev.seoul_diningtable.data.map

import com.jiwondev.seoul_diningtable.data.common.NetworkModule
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.MapApi
import com.jiwondev.seoul_diningtable.data.map.repository.ReverseGeocodingImpl
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object MapModule {
    @Singleton
    @Provides
    fun provideMapApi(retrofit: Retrofit) : MapApi {
        return retrofit.create(MapApi::class.java)
    }

    fun provideMapRepository(mapApi: MapApi): MapRepository {
        return ReverseGeocodingImpl(mapApi)
    }
}