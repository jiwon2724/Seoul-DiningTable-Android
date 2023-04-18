package com.jiwondev.seoul_diningtable.domain.map.usecase

import com.jiwondev.seoul_diningtable.domain.map.entity.geocoding.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeocodingUseCase @Inject constructor(private val mapRepository: MapRepository) {
    suspend fun convertCoordinate(coords: String) : Flow<ReverseGeocodingDto> {
        return mapRepository.getReverseGeocoding(coords)
    }
}