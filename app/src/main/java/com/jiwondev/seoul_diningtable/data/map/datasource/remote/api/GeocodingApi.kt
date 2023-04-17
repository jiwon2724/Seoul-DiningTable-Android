package com.jiwondev.seoul_diningtable.data.map.datasource.remote.api

import com.jiwondev.seoul_diningtable.BuildConfig
import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GeocodingApi {
    @GET("gc")
    suspend fun getReverseGeocodingResult(
        @Header("X-NCP-APIGW-API-KEY-ID") id: String = BuildConfig.NAVER_CLIENT_ID,
        @Header("X-NCP-APIGW-API-KEY") secret: String = BuildConfig.NAVER_CLIENT_SECRET,
        @Query("coords") coords: String,
        @Query("output") output: String = "json"
    ) : Response<ReverseGeocodingDto>
}