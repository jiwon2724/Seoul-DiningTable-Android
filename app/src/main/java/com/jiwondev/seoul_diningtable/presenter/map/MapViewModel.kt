package com.jiwondev.seoul_diningtable.presenter.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.domain.map.entity.ReverseGeocodingDto
import com.jiwondev.seoul_diningtable.domain.map.usecase.MapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val mapUseCase: MapUseCase) : ViewModel() {
    var flow = flowOf<ReverseGeocodingDto>()

    fun test(coords: String = "126.9779692,37.566535") = viewModelScope.launch {
        flow = mapUseCase.convertCoordinate(coords)
    }
}