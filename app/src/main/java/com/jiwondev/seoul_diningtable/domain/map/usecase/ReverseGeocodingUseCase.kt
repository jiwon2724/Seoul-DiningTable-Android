package com.jiwondev.seoul_diningtable.domain.map.usecase

import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow

class ReverseGeocodingUseCase(private val mapRepository: MapRepository) {
    suspend fun convertCoordinate(coords: String) : Flow<ReverseGeocodingDto> {
        return mapRepository.getReverseGeocoding(coords)
    }
}