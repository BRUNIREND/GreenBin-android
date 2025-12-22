package com.example.data.auth.repository

import com.example.data.R
import com.example.domain.categories.model.Category
import com.example.domain.categories.repository.ICategoryRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor() : ICategoryRepository {
    override fun getCategories(): List<Category> = listOf(
        Category("plastic", "Пластик", R.drawable.ic_plastic),
        Category("paper", "Макулатура", R.drawable.ic_paper),
        Category("metal", "Металл", R.drawable.ic_metal),
        Category("glass", "Стекло", R.drawable.ic_glass),
        Category("batteries", "Батарейки", R.drawable.ic_battery),
        Category("medecine", "Медецинские отходы", R.drawable.ic_medicine),
        Category("Tecnice", "Техника", R.drawable.ic_tecnice),
        Category("Mishlen", "Шины", R.drawable.ic_mishlen),
        // добавь остальные
    )
}