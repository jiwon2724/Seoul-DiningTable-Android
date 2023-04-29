package com.jiwondev.seoul_diningtable.data.auth.datasource.reomote

import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterDto
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterRequest
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.GET_VALIDATION
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.POST_LOGIN_AND_SIGN_UP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @GET(GET_VALIDATION)
    suspend fun getUserValidation(
        @Query("userEmail") userEmail: String
    ) : Response<ValidationDto>

    @POST(POST_LOGIN_AND_SIGN_UP)
    suspend fun postSignAndLogin(
        @Body request: RegisterRequest
    ) : Response<RegisterDto>
}