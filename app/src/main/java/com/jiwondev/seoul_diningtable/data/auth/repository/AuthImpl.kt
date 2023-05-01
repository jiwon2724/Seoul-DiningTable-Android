package com.jiwondev.seoul_diningtable.data.auth.repository

import com.jiwondev.seoul_diningtable.data.auth.datasource.reomote.AuthApi
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterDto
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterRequest
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.repository.AuthRepository
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun getUserValidation(userEmail: String): Flow<BaseResult<ValidationDto>> {
        return flow {
            val apiResult = authApi.getUserValidation(userEmail)

            if(apiResult.isSuccessful) {
                emit(BaseResult.Success(apiResult.body() ?: ValidationDto()))
            } else {
                // TODO : StatusCode 3XX ~ 5XX번 분기 -> 실패일경우 ValidationDto로 받아오는지 확인.
                emit(BaseResult.Success(apiResult.body() ?: ValidationDto()))
            }
        }
    }

    override suspend fun postUserRegister(userRequest: RegisterRequest): Flow<BaseResult<RegisterDto>> {
        return flow {
            val apiResult = authApi.postSignAndLogin(userRequest)

            if(apiResult.isSuccessful) {
                emit(BaseResult.Success(apiResult.body() ?: RegisterDto()))
            } else {
                // TODO : StatusCode 3XX ~ 5XX번 분기 -> 실패일경우 RegisterDto로 받아오는지 확인.
                emit(BaseResult.Success(apiResult.body() ?: RegisterDto()))
            }
        }
    }
}