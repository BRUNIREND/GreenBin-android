package com.example.data.auth.repository

import com.example.data.R
import com.example.domain.map.model.RecyclingPoint
import com.example.domain.map.repository.IRecyclingPointRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RecyclingPointRepositoryImpl @Inject constructor() : IRecyclingPointRepository {

    override fun getAllPoints(): List<RecyclingPoint> = hardcodedPoints

    override fun getPointsByCategory(categoryId: String): List<RecyclingPoint> =
        hardcodedPoints.filter { it.categoryId == categoryId }

    private val hardcodedPoints = listOf(
        // Пластик
        RecyclingPoint(
            id = "1",
            name = "Пункт на Тверской",
            latitude = 55.758,
            longitude = 37.615,
            categoryId = "plastic",
            iconRes = R.drawable.ic_plastic,
            address = "ул. Тверская, д. 10",
            phone = "+7 (495) 123-45-67",
            workingHours = "Ежедневно 10:00–20:00",
            acceptedMaterials = "ПЭТ-бутылки, пластиковые пакеты, контейнеры",
            categoryName = "Пластик"
        ),
//        RecyclingPoint("2", "Эко-точка в Парке Горького", 55.730, 37.600, "plastic", R.drawable.ic_plastic),
//
//        // Стекло
//        RecyclingPoint("3", "Контейнер у Кремля", 55.752, 37.622, "glass", R.drawable.ic_glass),
//        RecyclingPoint("4", "Сбор стекла на Арбате", 55.750, 37.590, "glass", R.drawable.ic_glass),
//
//        // Батарейки
//        RecyclingPoint("5", "Эко-бокс в МЕГЕ", 55.650, 37.480, "batteries", R.drawable.ic_battery),
//
//        // Бумага
//        RecyclingPoint("6", "Контейнер в Зарядье", 55.750, 37.630, "paper", R.drawable.ic_paper)
    )
}