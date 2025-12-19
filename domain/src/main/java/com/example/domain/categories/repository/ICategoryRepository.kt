package com.example.domain.categories.repository

import com.example.domain.categories.model.Category

interface ICategoryRepository {
    fun getCategories(): List<Category>
}
