package com.example.greenbin.features.feature_learning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.learning.usecase.GetLessonsUseCase
import com.example.domain.learning.usecase.GetQuestionsUseCase
import com.example.domain.learning.usecase.GetTestsUseCase
import com.example.domain.learning.usecase.GetTheoryCategoriesUseCase
import com.example.domain.learning.usecase.GetTopicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LearningViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase,
    private val getTheoryCategoriesUseCase: GetTheoryCategoriesUseCase,
    private val getLessonsUseCase: GetLessonsUseCase,
    private val getTestsUseCase: GetTestsUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    val topics = getTopicsUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun getTheoryCategories(topicId: String) = getTheoryCategoriesUseCase(topicId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun getTests(topicId: String) = getTestsUseCase(topicId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun getQuestions(testId: String) = getQuestionsUseCase(testId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getLessons(topicId: String) = getLessonsUseCase(topicId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}