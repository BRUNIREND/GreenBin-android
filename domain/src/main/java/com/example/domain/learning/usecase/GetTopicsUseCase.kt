package com.example.domain.learning.usecase

import com.example.domain.learning.model.LearningTopic
import com.example.domain.learning.repository.ILearningRepository
import kotlinx.coroutines.flow.Flow

class GetTopicsUseCase (
    private val repository: ILearningRepository
) {
    operator fun invoke(): Flow<List<LearningTopic>> = repository.getTopics()
}