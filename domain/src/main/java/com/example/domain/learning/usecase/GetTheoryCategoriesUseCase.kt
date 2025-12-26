package com.example.domain.learning.usecase

import com.example.domain.learning.model.TheoryCategory
import com.example.domain.learning.repository.ILearningRepository
import kotlinx.coroutines.flow.Flow

class GetTheoryCategoriesUseCase (
    private val repository: ILearningRepository
) {
    operator fun invoke(topicId: String): Flow<List<TheoryCategory>> = repository.getTheoryCategories(topicId)
}