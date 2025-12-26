package com.example.domain.learning.usecase

import com.example.domain.learning.model.Lesson
import com.example.domain.learning.repository.ILearningRepository
import kotlinx.coroutines.flow.Flow

class GetLessonsUseCase (
    private val repository: ILearningRepository
) {
    operator fun invoke(topicId: String): Flow<List<Lesson>> = repository.getLessons(topicId)
}