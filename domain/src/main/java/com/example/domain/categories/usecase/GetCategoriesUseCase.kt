package com.example.domain.categories.usecase

import com.example.domain.categories.model.Category
import com.example.domain.categories.repository.ICategoryRepository

class GetCategoriesUseCase (
    private val repository: ICategoryRepository
){
    operator fun invoke(): List<Category> = repository.getCategories()
}