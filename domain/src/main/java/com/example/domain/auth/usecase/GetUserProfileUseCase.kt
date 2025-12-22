package com.example.domain.auth.usecase

import com.example.domain.auth.repository.IAuthRepository
import com.example.domain.auth.model.User


class GetUserProfileUseCase(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(uid: String): User? {
        return authRepository.getUserProfile(uid)
    }
}