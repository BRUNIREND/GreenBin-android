package com.example.domain.map.usecase

import com.example.domain.map.model.RecyclingPoint
import com.example.domain.map.repository.IRecyclingPointRepository

class GetPointsByCategoryUseCase (
    private val repository: IRecyclingPointRepository
) {
    operator fun invoke(categoryId: String? = null): List<RecyclingPoint> {
        return if (categoryId == null) repository.getAllPoints()
        else repository.getPointsByCategory(categoryId)
    }
}