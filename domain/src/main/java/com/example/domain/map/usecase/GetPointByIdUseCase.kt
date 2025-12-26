package com.example.domain.map.usecase

import com.example.domain.map.model.RecyclingPoint
import com.example.domain.map.repository.IRecyclingPointRepository

class GetPointByIdUseCase(
    private val repository: IRecyclingPointRepository
) {
    operator fun invoke(pointId: String): RecyclingPoint? {
        return repository.getAllPoints().find { it.id == pointId }
    }
}