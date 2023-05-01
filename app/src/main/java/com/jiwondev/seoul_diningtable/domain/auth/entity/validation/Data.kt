package com.jiwondev.seoul_diningtable.domain.auth.entity.validation

data class Data(
    val createAt: String,
    val id: Int,
    val isState: Boolean,
    val userEmail: String,
    val userName: String,
    val userType: String
)