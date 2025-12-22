package com.example.domain.auth.usecase

import com.example.domain.auth.model.User
import com.example.domain.auth.repository.IAuthRepository

class SaveUserProfileUseCase(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return authRepository.saveUserProfile(user)
    }
}