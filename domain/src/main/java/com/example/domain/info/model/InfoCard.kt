package com.example.domain.info.model

data class InfoCard (
    val id: String,
    val title: String,
    val description: String,
    val imageRes: Int,
    val categoryId: String?
)