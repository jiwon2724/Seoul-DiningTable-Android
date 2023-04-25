package com.jiwondev.seoul_diningtable.domain.auth.validation.repository

import com.jiwondev.seoul_diningtable.domain.auth.validation.entity.ValidationDto
import com.jiwondev.seoul_diningtable.domain.map.entity.geocoding.ReverseGeocodingDto
import kotlinx.coroutines.flow.Flow

interface ValidationRepository {
    suspend fun getUserValidation(userEmail: String): Flow<ValidationDto>
}