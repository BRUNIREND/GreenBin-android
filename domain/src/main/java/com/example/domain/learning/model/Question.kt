package com.example.domain.learning.model

data class Question(
    val id: String,
    val text: String,
    val options: List<String>,   // варианты ответов
    val correctOptionIndex: Int  // индекс правильного ответа
)