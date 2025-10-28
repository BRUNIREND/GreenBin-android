package com.example.data.auth.di

import com.example.domain.auth.repository.AuthRepository
import com.example.domain.auth.usecase.CheckAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideCheckAuthUseCase(
        authRepository: AuthRepository
    ): CheckAuthUseCase {
        return CheckAuthUseCase(authRepository)
    }
}