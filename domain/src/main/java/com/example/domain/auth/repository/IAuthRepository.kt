package com.example.domain.auth.repository

interface IAuthRepository {
    suspend fun isUserLoggedIn(): Boolean
}