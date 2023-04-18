package com.jiwondev.seoul_diningtable.data.map.datasource.remote.api

import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import retrofit2.Response
import retrofit2.http.GET

interface SeoulOpenApi {
    @GET("ListNecessariesPricesService/1/30/")
    suspend fun getProductList() : Response<StoreProductListDto>
}