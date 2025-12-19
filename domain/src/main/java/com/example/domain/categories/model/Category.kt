package com.example.domain.categories.model

data class Category(
    val id: String,
    val name: String,
    val iconRes: Int? = null,
    val description: String? = null
)