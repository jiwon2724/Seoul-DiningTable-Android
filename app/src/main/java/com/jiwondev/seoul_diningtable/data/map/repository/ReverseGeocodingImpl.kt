package com.jiwondev.seoul_diningtable.data.map.repository

import android.util.Log
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.DefaultApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.GeocodingApi
import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.entity.StoreInfoDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReverseGeocodingImpl @Inject constructor(
    private val geocodingApi: GeocodingApi,
    private val defaultApi: DefaultApi
    ): MapRepository {
    override suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto> {
        return flow {
            val response = geocodingApi.getReverseGeocodingResult(coords = coords)
            Log.d("mapResponse : ", response.body().toString())

            if(response.isSuccessful) {
                emit((response.body() ?: emptyFlow<ReverseGeocodingDto>()) as ReverseGeocodingDto)
            }
        }
    }

    override suspend fun getSearchBoroughStore(borough: Int): Flow<StoreInfoDto> {
        return flow {
            val response = defaultApi.getBoroughStore(borough)

            if(response.isSuccessful) {
                emit((response.body() ?: emptyFlow<StoreInfoDto>()) as StoreInfoDto)
            }
        }
    }
}