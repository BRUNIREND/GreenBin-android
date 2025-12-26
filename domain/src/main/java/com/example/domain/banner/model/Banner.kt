package com.example.domain.banner.model

data class Banner(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageRes: Int,
    val cards: List<StoryCard>
//    @DrawableRes val imageRes: Int
)
