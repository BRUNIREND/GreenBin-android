package com.example.data.auth.di

import com.example.domain.auth.repository.IAuthRepository
import com.example.domain.auth.usecase.CheckAuthUseCase
import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.auth.usecase.RegisterUseCase
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
        IAuthRepository: IAuthRepository
    ): CheckAuthUseCase {
        return CheckAuthUseCase(IAuthRepository)
    }

    @Provides
    fun provideLoginUseCase(repository: IAuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideRegisterUseCase(repository: IAuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }
}