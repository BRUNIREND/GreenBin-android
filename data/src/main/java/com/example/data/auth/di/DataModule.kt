package com.example.data.auth.di
import com.example.data.auth.repository.AuthRepositoryImpl
import com.example.data.auth.repository.BannerRepositoryImpl
import com.example.data.auth.repository.CategoryRepositoryImpl
import com.example.data.auth.repository.InfoCardRepositoryImpl
import com.example.data.auth.repository.LearningRepositoryImpl
import com.example.data.auth.repository.RecyclingPointRepositoryImpl
import com.example.domain.auth.repository.IAuthRepository
import com.example.domain.banner.repository.IBannerRepository
import com.example.domain.categories.repository.ICategoryRepository
import com.example.domain.info.repository.IInfoCardRepository
import com.example.domain.learning.repository.ILearningRepository
import com.example.domain.map.repository.IRecyclingPointRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): IAuthRepository

    @Binds
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): ICategoryRepository

    @Binds
    fun bindInfoCardRepository(impl: InfoCardRepositoryImpl): IInfoCardRepository
    @Binds
    fun bindBannerRepository(
        impl: BannerRepositoryImpl
    ): IBannerRepository

    @Binds
    fun bindRecyclingPointRepository(
        impl: RecyclingPointRepositoryImpl
    ): IRecyclingPointRepository
    @Binds
    fun bindLearningRepository(
        impl: LearningRepositoryImpl
    ): ILearningRepository
}