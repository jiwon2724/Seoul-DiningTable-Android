package com.jiwondev.seoul_diningtable.domain.auth.entity.validation

data class ValidationDto(
    val data: Data? = null,
    val message: String = "",
    val status: String = ""
)