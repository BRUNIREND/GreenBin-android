package com.example.domain.auth.repository

import com.example.domain.auth.model.User

interface IAuthRepository {
    suspend fun register(email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun isUserLoggedIn(): Boolean

    val currentUser: User?
}