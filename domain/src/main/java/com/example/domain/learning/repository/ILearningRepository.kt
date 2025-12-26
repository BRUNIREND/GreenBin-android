package com.example.domain.learning.repository

import com.example.domain.learning.model.LearningTest
import com.example.domain.learning.model.LearningTopic
import com.example.domain.learning.model.TheoryCategory
import kotlinx.coroutines.flow.Flow

interface ILearningRepository {
    fun getTopics(): Flow<List<LearningTopic>>
    fun getTheoryCategories(topicId: String): Flow<List<TheoryCategory>>
    fun getTests(topicId: String): Flow<List<LearningTest>>
}