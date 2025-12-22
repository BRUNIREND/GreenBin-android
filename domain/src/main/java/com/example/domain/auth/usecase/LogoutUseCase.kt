package com.example.domain.auth.usecase

import com.example.domain.auth.repository.IAuthRepository

class LogoutUseCase (
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.logout()
    }
}