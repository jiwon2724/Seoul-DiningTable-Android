package com.jiwondev.seoul_diningtable.domain.map.repository


import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import com.jiwondev.seoul_diningtable.domain.map.entity.geocoding.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import com.jiwondev.seoul_diningtable.domain.map.entity.store.StoreInfoDto
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    suspend fun getReverseGeocoding(coords: String): Flow<ReverseGeocodingDto>
    suspend fun getSearchBoroughStore(borough: Int): Flow<BaseResult<StoreInfoDto>>
    suspend fun getStoreProductList(): Flow<BaseResult<StoreProductListDto>>
}


