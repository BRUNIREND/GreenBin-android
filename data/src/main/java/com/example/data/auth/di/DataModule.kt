package com.example.data.auth.di
import com.example.data.auth.repository.AuthRepositoryImpl
import com.example.domain.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}