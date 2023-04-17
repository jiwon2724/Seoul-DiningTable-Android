package com.jiwondev.seoul_diningtable.presenter.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.usecase.GeocodingUseCase
import com.jiwondev.seoul_diningtable.domain.map.usecase.StoreSearchUseCase
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.JONGROGU
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.JOONGU
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.SUNGDONGU
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.YONGSANGU
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val geocodingUseCase: GeocodingUseCase,
    private val storeSearchUseCase: StoreSearchUseCase) : ViewModel() {

    var flow = flowOf<ReverseGeocodingDto>()

    /**
     * 1. 지오코딩 결과 자치구 코드로 변환
     * 2. 해당 자치구 코드로 open api 호출
     * 3. 해당 자치구 코드로 base api 호출
     *
     * **/
    fun test(coords: String = "126.9779692,37.566535") = viewModelScope.launch {
        geocodingUseCase.convertCoordinate(coords).flatMapConcat {
            val boroughCode = boroughToCode(it.results[0].region.area2.toString())
            storeSearchUseCase.getStoreInfo(boroughCode)
        }.collect()
    }

    // TODO : 자치구 -> 코드 변환 로직 개선방법 생각해야해.
    private fun boroughToCode(borough: String): Int {
        return when(borough) {
            "종로구" -> JONGROGU
            "중구" -> JOONGU
            "용산구" -> YONGSANGU
            else -> SUNGDONGU
        }
    }
}