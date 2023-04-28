package com.jiwondev.seoul_diningtable.data.user.model

data class UserPreference(
    val autoLogin: Boolean,
    val userEmail: String,
    val lastLoginSns: String,
    val lastLoginType: String
)
