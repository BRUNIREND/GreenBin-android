package com.example.data.auth.repository

import com.example.domain.auth.repository.AuthRepository

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {
    override suspend fun isUserLoggedIn(): Boolean {
        // TODO: проверить Firebase, DataStore, SharedPrefs
        return false
    }
}