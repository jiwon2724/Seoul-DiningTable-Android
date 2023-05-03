package com.jiwondev.seoul_diningtable.data.map

import com.jiwondev.seoul_diningtable.data.common.NetworkModule
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.DefaultApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.GeocodingApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.SeoulOpenApi
import com.jiwondev.seoul_diningtable.data.map.repository.MapImpl
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object MapModule {
    @Singleton
    @Provides
    fun provideGeocodingApi(@Named("geocoding") retrofit: Retrofit) : GeocodingApi {
        return retrofit.create(GeocodingApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultApi(@Named("default") retrofit: Retrofit) : DefaultApi {
        return retrofit.create(DefaultApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSeoulStoreProductApi(@Named("product") retrofit: Retrofit) : SeoulOpenApi {
        return retrofit.create(SeoulOpenApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMapRepository(
        mapApi: GeocodingApi,
        defaultApi: DefaultApi,
        seoulApi: SeoulOpenApi
    ): MapRepository {
        return MapImpl(mapApi, defaultApi, seoulApi)
    }
}