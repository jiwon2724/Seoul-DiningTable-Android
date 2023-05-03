package com.jiwondev.seoul_diningtable.presenter.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import com.jiwondev.seoul_diningtable.domain.map.entity.product.StoreProductListDto
import com.jiwondev.seoul_diningtable.domain.map.entity.store.StoreInfoDto
import com.jiwondev.seoul_diningtable.domain.map.usecase.GeocodingUseCase
import com.jiwondev.seoul_diningtable.domain.map.usecase.StoreProductUseCase
import com.jiwondev.seoul_diningtable.domain.map.usecase.StoreSearchUseCase
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.JONGROGU
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.JOONGU
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.SUNGDONGU
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.YONGSANGU
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val geocodingUseCase: GeocodingUseCase,
    private val storeSearchUseCase: StoreSearchUseCase,
    private val storeProductUseCase: StoreProductUseCase
) : ViewModel() {

    private val _productUiState: MutableStateFlow<ProductUiState> = MutableStateFlow(ProductUiState.Init)
    val productUiState: StateFlow<ProductUiState> = _productUiState.asStateFlow()

    private val _storeUiState: MutableStateFlow<StoreUiState> = MutableStateFlow(StoreUiState.Init)
    val storeUiState: StateFlow<StoreUiState> = _storeUiState.asStateFlow()


    // TODO : 위, 경도 실데이터로 바꿔야해!
    fun getBoroughStore(coords: String = "126.9779692,37.566535")  {
        viewModelScope.launch {
            geocodingUseCase.convertCoordinate(coords).flatMapConcat {
                val boroughCode = boroughToCode(it.results[0].region.area2.toString())
                storeSearchUseCase.getStoreInfo(boroughCode)
            }
            .onStart { setStoreLoading() }
            .catch {
                hideStoreLoading()

            }
            .collect { response ->
                hideProductLoading()

                when(response) {
                    is BaseResult.Success -> {
                        _storeUiState.value =  StoreUiState.IsSuccess(response.data)
                    }

                    is BaseResult.Error -> {

                    }
                }
            }
        }
    }

    // TODO : 함수이름 수정할 것.
    fun getProduct() {
        viewModelScope.launch {
            storeProductUseCase.getProductList()
                .onStart { setProductLoading() }
                .catch {
                    hideProductLoading()
                }
                .collect { response ->
                    hideProductLoading()

                    when(response) {
                        is BaseResult.Success -> {
                            _productUiState.value = ProductUiState.IsSuccess(response.data)
                        }

                        is BaseResult.Error -> {

                        }
                    }
                }
        }
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

    private fun setStoreLoading() { _productUiState.value = ProductUiState.IsLoading(true) }
    private fun hideStoreLoading() { _productUiState.value = ProductUiState.IsLoading(false) }

    private fun setProductLoading() { _storeUiState.value = StoreUiState.IsLoading(true) }
    private fun hideProductLoading() { _storeUiState.value = StoreUiState.IsLoading(false) }
}

sealed class ProductUiState {
    object Init : ProductUiState()
    data class IsLoading(val isLoading: Boolean = false) : ProductUiState()
    data class IsSuccess(val successDto: StoreProductListDto) : ProductUiState()
    data class IsFailed(val failedDto: String) : ProductUiState()
}

sealed class StoreUiState {
    object Init : StoreUiState()
    data class IsLoading(val isLoading: Boolean = false) : StoreUiState()
    data class IsSuccess(val successDto: StoreInfoDto) : StoreUiState()
    data class IsFailed(val failedDto: String) : StoreUiState()
}