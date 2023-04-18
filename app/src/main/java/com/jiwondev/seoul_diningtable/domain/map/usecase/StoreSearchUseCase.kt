package com.jiwondev.seoul_diningtable.domain.map.usecase

import com.jiwondev.seoul_diningtable.domain.map.entity.store.StoreInfoDto
import com.jiwondev.seoul_diningtable.domain.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreSearchUseCase @Inject constructor(private val mapRepository: MapRepository) {
    suspend fun getStoreInfo(borough: Int): Flow<StoreInfoDto> {
        return mapRepository.getSearchBoroughStore(borough)
    }
}