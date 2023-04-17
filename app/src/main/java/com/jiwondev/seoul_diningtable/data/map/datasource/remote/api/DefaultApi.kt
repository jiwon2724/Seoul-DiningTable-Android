package com.jiwondev.seoul_diningtable.data.map.datasource.remote.api

import com.jiwondev.seoul_diningtable.domain.map.entity.StoreInfoDto
import com.jiwondev.seoul_diningtable.presenter.common.Constant
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.MART
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DefaultApi {
    @GET(MART)
    suspend fun getBoroughStore(
        @Query("bo_id") bo_id: Int
    ): Response<StoreInfoDto>
}

