package com.jiwondev.seoul_diningtable.domain.auth.entity.register

data class RegisterRequest(
    val userEmail: String,
    val userName: String,
    val userType: String
)
