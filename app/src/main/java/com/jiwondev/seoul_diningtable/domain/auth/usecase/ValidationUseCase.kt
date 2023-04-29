package com.jiwondev.seoul_diningtable.domain.auth.usecase

import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.repository.AuthRepository
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidationUseCase @Inject constructor(private val validationRepository: AuthRepository) {
    suspend fun getUserValidation(userEmail: String): Flow<BaseResult<ValidationDto>> {
        return validationRepository.getUserValidation(userEmail)
    }
}
