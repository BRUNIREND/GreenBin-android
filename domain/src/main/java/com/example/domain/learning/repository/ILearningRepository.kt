package com.example.domain.learning.repository

import com.example.domain.learning.model.LearningTopic
import com.example.domain.learning.model.Lesson
import com.example.domain.learning.model.Question
import com.example.domain.learning.model.Test
import com.example.domain.learning.model.TheoryCategory
import kotlinx.coroutines.flow.Flow

interface ILearningRepository {
    fun getTopics(): Flow<List<LearningTopic>>
    fun getTheoryCategories(topicId: String): Flow<List<TheoryCategory>>
//    fun getTests(topicId: String): Flow<List<LearningTest>>
    fun getQuestions(testId: String): Flow<List<Question>>

    fun getLessons(topicId: String): Flow<List<Lesson>>
    fun getTests(topicId: String): Flow<List<Test>>
}