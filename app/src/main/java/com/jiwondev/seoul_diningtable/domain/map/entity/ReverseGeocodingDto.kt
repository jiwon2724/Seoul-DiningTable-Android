package com.jiwondev.seoul_diningtable.domain.map.entity

data class ReverseGeocodingDto(
    val results: List<Result>,
    val status: Status
)