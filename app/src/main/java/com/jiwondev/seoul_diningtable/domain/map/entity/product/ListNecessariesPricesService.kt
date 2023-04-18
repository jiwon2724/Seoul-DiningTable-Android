package com.jiwondev.seoul_diningtable.domain.map.entity.product

data class ListNecessariesPricesService(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)