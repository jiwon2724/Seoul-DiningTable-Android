package com.jiwondev.seoul_diningtable.domain.auth.validation.entity

data class ValidationDto(
    val data: Data?,
    val message: String = "",
    val status: String = ""
)