package com.jiwondev.seoul_diningtable.data.map.repository

import android.util.Log
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.DefaultApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.GeocodingApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.SeoulOpenApi
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import com.jiwondev.seoul_diningtable.domain.map.entity.geocoding.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import com.jiwondev.seoul_diningtable.domain.map.entity.store.StoreInfoDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MapImpl @Inject constructor(
    private val geocodingApi: GeocodingApi,
    private val defaultApi: DefaultApi,
    private val seoulOpenApi: SeoulOpenApi
    ): MapRepository {
    override suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto> {
        return flow {
            val response = geocodingApi.getReverseGeocodingResult(coords = coords)

            if(response.isSuccessful) {
                emit(response.body() ?: ReverseGeocodingDto())
            }
        }
    }

    override suspend fun getSearchBoroughStore(borough: Int): Flow<BaseResult<StoreInfoDto>> {
        return flow {
            val apiResult = defaultApi.getBoroughStore(borough)

            if(apiResult.isSuccessful) {
                emit(BaseResult.Success(apiResult.body() ?: StoreInfoDto()))
            } else {
                // TODO : StatusCode 3XX ~ 5XX번 분기 -> 실패일경우 ValidationDto로 받아오는지 확인.
                emit(BaseResult.Error(apiResult.body() ?: StoreInfoDto()))
            }
        }
    }

    override suspend fun getStoreProductList(): Flow<BaseResult<StoreProductListDto>> {
        return flow {
            val apiResult = seoulOpenApi.getProductList()

            if(apiResult.isSuccessful) {
                emit(BaseResult.Success(apiResult.body() ?: StoreProductListDto()))
            } else {
                // TODO : StatusCode 3XX ~ 5XX번 분기 -> 실패일경우 ValidationDto로 받아오는지 확인.
                emit(BaseResult.Error(apiResult.body() ?: StoreProductListDto()))
            }
        }
    }
}