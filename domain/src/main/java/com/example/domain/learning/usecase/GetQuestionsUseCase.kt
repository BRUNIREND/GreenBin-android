package com.example.domain.learning.usecase

import com.example.domain.learning.model.Question
import com.example.domain.learning.repository.ILearningRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionsUseCase(
    private val repository: ILearningRepository
) {
    operator fun invoke(testId: String): Flow<List<Question>> = repository.getQuestions(testId)
}

