package com.jiwondev.seoul_diningtable.domain.auth.repository

import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterDto
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterRequest
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getUserValidation(userEmail: String): Flow<BaseResult<ValidationDto>>
    suspend fun postUserRegister(userRequest: RegisterRequest): Flow<BaseResult<RegisterDto>>
}