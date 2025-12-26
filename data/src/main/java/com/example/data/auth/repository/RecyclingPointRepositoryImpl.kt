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
        // Пластик в Красноярске
        RecyclingPoint("1", "Пункт на проспекте Мира", 56.0105, 92.8672, "plastic", R.drawable.ic_plastic, "просп. Мира, 10", "+7 (391) 123-45-67", "Ежедневно 10:00–20:00", "ПЭТ-бутылки, пластиковые пакеты"),
        RecyclingPoint("2", "Эко-точка в Центральном парке", 56.0150, 92.8600, "plastic", R.drawable.ic_plastic, "ул. Карла Маркса, 78", "+7 (391) 234-56-78", "Пн-Сб 9:00–18:00", "Пластиковые контейнеры, пакеты"),

        // Стекло
        RecyclingPoint("3", "Контейнер у ТРЦ Планета", 56.0080, 92.8700, "glass", R.drawable.ic_glass, "ул. 9 Мая, 77", "+7 (391) 345-67-89", "Ежедневно 10:00–22:00", "Стеклянные бутылки, банки"),
        RecyclingPoint("4", "Сбор стекла на ул. Ленина", 56.0120, 92.8650, "glass", R.drawable.ic_glass, "ул. Ленина, 5", "", "Круглосуточно", "Любое стекло"),

        // Батарейки
        RecyclingPoint("5", "Эко-бокс в супермаркете О'Кей", 56.0050, 92.8750, "batteries", R.drawable.ic_battery, "ул. Взлётная, 5", "+7 (391) 456-78-90", "Ежедневно 8:00–23:00", "Батарейки, аккумуляторы"),

        // Бумага
        RecyclingPoint("6", "Контейнер в парке на острове Отдыха", 56.0200, 92.8550, "paper", R.drawable.ic_paper, "остров Отдыха, 1", "+7 (391) 567-89-01", "Круглосуточно", "Газеты, картон, бумага"),

        // Дополнительные пункты для разнообразия
        RecyclingPoint("7", "Пункт переработки металла на ул. Калинина", 55.9990, 92.8800, "metal", R.drawable.ic_metal, "ул. Калинина, 43", "+7 (391) 567-89-01", "Пн-Пт 9:00–18:00", "Металлолом, банки"),
        RecyclingPoint("8", "Эко-центр на ул. Советская", 56.0250, 92.8500, "plastic", R.drawable.ic_plastic, "ул. Советская, 12", "+7 (391) 678-90-12", "Ежедневно 9:00–19:00", "Пластик всех типов")
    )
//    private val hardcodedPoints = listOf(
//        // Пластик
//        RecyclingPoint(
//            id = "1",
//            name = "Пункт на Тверской",
//            latitude = 55.758,
//            longitude = 37.615,
//            categoryId = "plastic",
//            iconRes = R.drawable.ic_plastic,
//            address = "ул. Тверская, д. 10",
//            phone = "+7 (495) 123-45-67",
//            workingHours = "Ежедневно 10:00–20:00",
//            acceptedMaterials = "ПЭТ-бутылки, пластиковые пакеты, контейнеры",
//            categoryName = "Пластик"
//        ),
////        RecyclingPoint("2", "Эко-точка в Парке Горького", 55.730, 37.600, "plastic", R.drawable.ic_plastic),
////
////        // Стекло
////        RecyclingPoint("3", "Контейнер у Кремля", 55.752, 37.622, "glass", R.drawable.ic_glass),
////        RecyclingPoint("4", "Сбор стекла на Арбате", 55.750, 37.590, "glass", R.drawable.ic_glass),
////
////        // Батарейки
////        RecyclingPoint("5", "Эко-бокс в МЕГЕ", 55.650, 37.480, "batteries", R.drawable.ic_battery),
////
////        // Бумага
////        RecyclingPoint("6", "Контейнер в Зарядье", 55.750, 37.630, "paper", R.drawable.ic_paper)
//    )
}