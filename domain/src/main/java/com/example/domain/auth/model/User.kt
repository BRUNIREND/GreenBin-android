package com.example.domain.auth.model

data class User(
    val uid: String,
    val email: String?,
    val displayName: String? = null,
//    val photoUrl: String? = null,
    val phone: String? = null,
    val address: String? = null
)
