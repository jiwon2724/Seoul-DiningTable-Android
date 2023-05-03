package com.jiwondev.seoul_diningtable.data.map.datasource.remote.api

import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.GET_PRICE_INFO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulOpenApi {
    /**
     *
     * START_INDEX : 요청시작위치
     * END_INDEX : 요청종료위치
     * M_NAME_SCH : 시장 이름
     * A_NAME : 품목이름
     * P_YEAR_MONTH : 년도 월
     * M_TYPE_NAME : 시장유형 구분 이름
     * M_GU_NAME : 자치구 이름
     *
     * **/

    @GET(GET_PRICE_INFO)
    suspend fun getProductList(
        @Path("START_INDEX") START_INDEX: Int = 1,
        @Path("END_INDEX") END_INDEX: Int = 1000,
        @Path("M_NAME_SCH") M_NAME_SCH: String = "",
        @Path("A_NAME") A_NAME: String = "",
        @Path("P_YEAR_MONTH") P_YEAR_MONTH: String = "",
        @Path("M_TYPE_NAME") M_TYPE_NAME: String = "",
        @Path("M_GU_NAME") M_GU_NAME: String = ""
    ) : Response<StoreProductListDto>
}