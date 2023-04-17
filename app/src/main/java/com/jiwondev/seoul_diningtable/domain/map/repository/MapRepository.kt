package com.jiwondev.seoul_diningtable.domain.map.repository

import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.entity.StoreInfoDto
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto>
    suspend fun getSearchBoroughStore(borough: Int): Flow<StoreInfoDto>
}