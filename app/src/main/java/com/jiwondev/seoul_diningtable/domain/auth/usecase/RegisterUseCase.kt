package com.jiwondev.seoul_diningtable.domain.auth.usecase

import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterDto
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterRequest
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.repository.AuthRepository
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun postUserRegister(userRequest: RegisterRequest): Flow<BaseResult<RegisterDto>> {
        return authRepository.postUserRegister(userRequest)
    }
}

