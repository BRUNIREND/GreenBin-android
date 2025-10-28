package com.example.domain.auth.usecase

import com.example.domain.auth.repository.AuthRepository

class CheckAuthUseCase(
    private val authRepository: AuthRepository  // ← просто параметр
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}