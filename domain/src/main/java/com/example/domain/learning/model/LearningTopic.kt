package com.example.domain.learning.model

data class LearningTopic(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconRes: Int,
    val totalLessons: Int,
    val completedLessons: Int
)
