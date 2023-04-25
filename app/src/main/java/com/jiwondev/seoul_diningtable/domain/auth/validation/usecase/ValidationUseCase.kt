package com.jiwondev.seoul_diningtable.domain.auth.validation.usecase

import com.jiwondev.seoul_diningtable.domain.auth.validation.entity.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.validation.repository.ValidationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidationUseCase @Inject constructor(private val validationRepository: ValidationRepository) {
    suspend fun getUserValidation(userEmail: String): Flow<ValidationDto> {
        return validationRepository.getUserValidation(userEmail)
    }
}
