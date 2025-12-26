package com.example.domain.map.model

data class RecyclingPoint (
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val categoryId: String,
    val iconRes: Int,


    // Для детального экрана
    val address: String = "",
    val phone: String = "",
    val workingHours: String = "Пн–Пт: 9:00–18:00",
    val acceptedMaterials: String = "Пластик, стекло, бумага",
    val categoryName: String = "",   // "Пластик", "Стекло" и т.д. (можно вычислить по categoryId)
)
