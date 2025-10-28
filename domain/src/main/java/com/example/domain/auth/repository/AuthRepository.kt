package com.example.domain.auth.repository

interface AuthRepository {
    suspend fun isUserLoggedIn(): Boolean
}