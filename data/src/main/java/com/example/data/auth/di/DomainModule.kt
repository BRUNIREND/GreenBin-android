package com.example.data.auth.di

import com.example.domain.auth.repository.IAuthRepository
import com.example.domain.auth.usecase.CheckAuthUseCase
import com.example.domain.auth.usecase.GetUserNameUseCase
import com.example.domain.auth.usecase.GetUserProfileUseCase
import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.auth.usecase.LogoutUseCase
import com.example.domain.auth.usecase.RegisterUseCase
import com.example.domain.auth.usecase.SaveUserProfileUseCase
import com.example.domain.banner.repository.IBannerRepository
import com.example.domain.banner.usecase.GetBannersUseCase
import com.example.domain.categories.repository.ICategoryRepository
import com.example.domain.categories.usecase.GetCategoriesUseCase
import com.example.domain.info.repository.IInfoCardRepository
import com.example.domain.info.usecase.GetInfoCardsUseCase
import com.example.domain.learning.repository.ILearningRepository
import com.example.domain.learning.usecase.GetTestsUseCase
import com.example.domain.learning.usecase.GetTheoryCategoriesUseCase
import com.example.domain.learning.usecase.GetTopicsUseCase
import com.example.domain.map.repository.IRecyclingPointRepository
import com.example.domain.map.usecase.GetPointByIdUseCase
import com.example.domain.map.usecase.GetPointsByCategoryUseCase
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
    fun provideCheckAuthUseCase(IAuthRepository: IAuthRepository): CheckAuthUseCase {
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

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repository: ICategoryRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetInfoCardsUseCase(repository: IInfoCardRepository): GetInfoCardsUseCase{
        return GetInfoCardsUseCase(repository)
    }

    @Provides
    @Singleton   // или без @Singleton, если не нужен
    fun provideGetUserNameUseCase(
        repository: IAuthRepository   // или AuthRepository
    ): GetUserNameUseCase {
        return GetUserNameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetBannersUseCase(repository: IBannerRepository): GetBannersUseCase =
        GetBannersUseCase(repository)


    @Provides
    @Singleton
    fun provideGetUserProfileUseCase(
        authRepository: IAuthRepository
    ): GetUserProfileUseCase {
        return GetUserProfileUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserProfileUseCase(repository: IAuthRepository): SaveUserProfileUseCase =
        SaveUserProfileUseCase(repository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: IAuthRepository): LogoutUseCase =
        LogoutUseCase(repository)
    @Provides
    @Singleton
    fun provideGetPointsByCategoryUseCase(
        repository: IRecyclingPointRepository
    ): GetPointsByCategoryUseCase = GetPointsByCategoryUseCase(repository)


    @Provides
    @Singleton
    fun provideGetPointByIdUseCase(
        repository: IRecyclingPointRepository
    ): GetPointByIdUseCase = GetPointByIdUseCase(repository)


    @Provides
    @Singleton
    fun provideGetTopicsUseCase(repository: ILearningRepository): GetTopicsUseCase =
        GetTopicsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTheoryCategoriesUseCase(repository: ILearningRepository): GetTheoryCategoriesUseCase =
        GetTheoryCategoriesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTestsUseCase(repository: ILearningRepository): GetTestsUseCase =
        GetTestsUseCase(repository)
}