package com.example.domain.learning.usecase

import com.example.domain.learning.model.LearningTest
import com.example.domain.learning.repository.ILearningRepository
import kotlinx.coroutines.flow.Flow

class GetTestsUseCase(
    private val repository: ILearningRepository
) {
    operator fun invoke(topicId: String): Flow<List<LearningTest>> = repository.getTests(topicId)
}