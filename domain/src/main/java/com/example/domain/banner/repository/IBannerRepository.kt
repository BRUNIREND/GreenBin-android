package com.example.domain.banner.repository

import com.example.domain.banner.model.Banner

interface IBannerRepository {
    fun getBanners(): List<Banner>
}