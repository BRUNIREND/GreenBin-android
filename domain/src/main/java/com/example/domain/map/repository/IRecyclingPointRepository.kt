package com.example.domain.map.repository

import com.example.domain.map.model.RecyclingPoint

interface IRecyclingPointRepository {
    fun getAllPoints(): List<RecyclingPoint>
    fun getPointsByCategory(categoryId: String): List<RecyclingPoint>
}