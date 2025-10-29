package com.example.domain.auth.usecase

import com.example.domain.auth.repository.IAuthRepository

class CheckAuthUseCase(
    private val IAuthRepository: IAuthRepository  // ← просто параметр
) {
    suspend operator fun invoke(): Boolean {
        return IAuthRepository.isUserLoggedIn()
    }
}