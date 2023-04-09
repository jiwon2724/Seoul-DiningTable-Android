package com.jiwondev.seoul_diningtable.data.map.repository

import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.MapApi
import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow

class ReverseGeocodingImpl(private val mapApi: MapApi): MapRepository {
    override suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto> {
        val test = mapApi.getReverseGeocodingResult(coords = coords)
    }

}