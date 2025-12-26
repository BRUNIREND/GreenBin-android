package com.example.domain.learning.model

data class Test(
    val id: String,
    val title: String,
    val questionsCount: Int,
    val progressPercent: Int,
    val isLocked: Boolean = false,
    val isCompleted: Boolean = false
)
