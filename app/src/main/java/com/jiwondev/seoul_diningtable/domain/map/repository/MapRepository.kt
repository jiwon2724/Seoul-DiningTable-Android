package com.jiwondev.seoul_diningtable.domain.map.repository

import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto>
}