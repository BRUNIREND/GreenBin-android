package com.example.domain.auth.usecase

import com.example.domain.auth.repository.IAuthRepository

class LoginUseCase (
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit>{
        return authRepository.login(email,password)
    }
}