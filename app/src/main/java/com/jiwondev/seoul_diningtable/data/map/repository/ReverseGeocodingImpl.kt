package com.jiwondev.seoul_diningtable.data.map.repository

import android.util.Log
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.DefaultApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.GeocodingApi
import com.jiwondev.seoul_diningtable.data.map.datasource.remote.api.SeoulOpenApi
import com.jiwondev.seoul_diningtable.domain.map.entity.geocoding.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import com.jiwondev.seoul_diningtable.domain.map.entity.store.StoreInfoDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


// TODO : !!(단언문) 제거하고 널처리 해야해.
// TODO : 클래스 이름 바꿔 !
class ReverseGeocodingImpl @Inject constructor(
    private val geocodingApi: GeocodingApi,
    private val defaultApi: DefaultApi,
    private val seoulOpenApi: SeoulOpenApi
    ): MapRepository {
    override suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto> {
        return flow {
            val response = geocodingApi.getReverseGeocodingResult(coords = coords)
            Log.d("mapResponse : ", response.body().toString())

            if(response.isSuccessful) {
                emit(response.body()!!)
            }
        }
    }

    override suspend fun getSearchBoroughStore(borough: Int): Flow<StoreInfoDto> {
        return flow {
            val response = defaultApi.getBoroughStore(borough)

            if(response.isSuccessful) {
                emit(response.body()!!)
            }
        }
    }

    override suspend fun getStoreProductList(): Flow<StoreProductListDto> {
        return flow {
            val response = seoulOpenApi.getProductList()

            Log.d("ProductList : ", response.body().toString())

            if(response.isSuccessful) {
                emit(response.body()!!)
            }
        }
    }
}