package com.jiwondev.seoul_diningtable.data.auth.repository

import android.util.Log
import com.jiwondev.seoul_diningtable.data.auth.datasource.reomote.AuthApi
import com.jiwondev.seoul_diningtable.domain.auth.validation.entity.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.validation.repository.ValidationRepository
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidationImpl @Inject constructor(private val authApi: AuthApi) : ValidationRepository {
    override suspend fun getUserValidation(userEmail: String): Flow<BaseResult<ValidationDto>> {
        return flow {
            Log.d("ValidationImpl : ", authApi.getUserValidation(userEmail).toString())
            val apiResult = authApi.getUserValidation(userEmail)

            if(apiResult.isSuccessful) {
                emit(BaseResult.Success(apiResult.body() ?: ValidationDto()))
            } else {
                // TODO : StatusCode 3XX ~ 5XX번 분기 -> 실패일경우 ValidationDto로 받아오는지 확인.
                emit(BaseResult.Success(apiResult.body() ?: ValidationDto()))
            }
        }
    }
}