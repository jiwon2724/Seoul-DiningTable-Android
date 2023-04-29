package com.jiwondev.seoul_diningtable.data.auth.datasource.reomote

import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.GET_VALIDATION
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {
    @GET(GET_VALIDATION)
    suspend fun getUserValidation(
        @Query("userEmail") userEmail: String
    ) : Response<ValidationDto>
}