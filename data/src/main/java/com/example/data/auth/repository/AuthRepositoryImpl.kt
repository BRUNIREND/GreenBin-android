package com.example.data.auth.repository

import com.example.domain.auth.repository.IAuthRepository

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): IAuthRepository {
    override suspend fun isUserLoggedIn(): Boolean {
        // TODO: проверить Firebase, DataStore, SharedPrefs
        return false
    }
}