package com.example.domain.auth.usecase

import com.example.domain.auth.repository.IAuthRepository

class GetUserNameUseCase(
    private val authRepository: IAuthRepository
){
    operator fun invoke(): String {

        return authRepository.currentUser?.displayName
            ?: authRepository.currentUser?.email?.substringBefore("@")
            ?: "Пользователь"
    }
}