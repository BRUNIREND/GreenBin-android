package com.example.greenbin.features.feature_main.presentation

import com.example.domain.banner.model.Banner
import com.example.domain.categories.model.Category
import com.example.domain.info.model.InfoCard

data class MainUiState (
    val userName: String = "",
    val categories: List<Category> = emptyList(),
    val infoCards: List<InfoCard> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val banners: List<Banner> = emptyList()
)



