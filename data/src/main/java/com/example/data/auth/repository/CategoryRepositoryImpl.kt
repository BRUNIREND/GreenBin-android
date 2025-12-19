package com.example.data.auth.repository

import com.example.domain.categories.model.Category
import com.example.domain.categories.repository.ICategoryRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor() : ICategoryRepository {
    override fun getCategories(): List<Category> {
        return listOf(
            Category("plastic", "Пластик"),
            Category("glass", "Стекло"),
            Category("batteries", "Батарейки"),
            Category("paper", "Бумага")
        )  // Hardcoded, добавь iconRes если заглушки
    }
}