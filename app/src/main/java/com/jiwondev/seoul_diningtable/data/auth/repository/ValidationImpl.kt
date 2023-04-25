package com.jiwondev.seoul_diningtable.data.auth.repository

import com.jiwondev.seoul_diningtable.data.auth.datasource.reomote.AuthApi
import com.jiwondev.seoul_diningtable.domain.auth.validation.entity.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.validation.repository.ValidationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidationImpl @Inject constructor(private val authApi: AuthApi) : ValidationRepository {
    override suspend fun getUserValidation(userEmail: String): Flow<ValidationDto> {
        return flow {
            val apiResult = authApi.getUserValidation(userEmail)

            Log.d("ValidationImpl : ", apiResult.toString())

            if(apiResult.isSuccessful) {
                emit(apiResult.body()!!)
            } else {

            }
        }
    }
}