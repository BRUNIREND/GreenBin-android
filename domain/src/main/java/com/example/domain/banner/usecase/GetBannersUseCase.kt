package com.example.domain.banner.usecase

import com.example.domain.banner.model.Banner
import com.example.domain.banner.repository.IBannerRepository

class GetBannersUseCase(
    private val repository: IBannerRepository
) {
    operator fun invoke(): List<Banner> = repository.getBanners()
}