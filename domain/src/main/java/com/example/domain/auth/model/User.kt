package com.example.domain.auth.model

data class User(
    val uid: String,
    val email: String?,
    val displayName: String? = null,
//    val photoUrl: String? = null,
//    val phoneNumber: String? = null,
)
