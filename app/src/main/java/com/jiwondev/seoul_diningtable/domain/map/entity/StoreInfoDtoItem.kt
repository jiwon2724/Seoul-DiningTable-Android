package com.jiwondev.seoul_diningtable.domain.map.entity

data class StoreInfoDtoItem(
    val martName: String,
    val martLatitude: Double,
    val martLongitude: Double,
    var boroughId: Int,
    var martId: Int
)