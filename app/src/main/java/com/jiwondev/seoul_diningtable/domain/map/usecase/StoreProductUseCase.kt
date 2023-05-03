package com.jiwondev.seoul_diningtable.domain.map.usecase

import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreProductUseCase @Inject constructor(private val mapRepository: MapRepository) {
    suspend fun getProductList() : Flow<BaseResult<StoreProductListDto>> {
        return mapRepository.getStoreProductList()
    }
}