package com.jiwondev.seoul_diningtable.data.map.datasource.remote.api

import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulOpenApi {
    @GET("ListNecessariesPricesService/1/1000/{M_TYPE_NAME}/{A_NAME}")
    suspend fun getProductList(
        @Path("M_TYPE_NAME") M_TYPE_NAME: String = "",
        @Path("A_NAME") A_NAME: String = ""
    ) : Response<StoreProductListDto>
}