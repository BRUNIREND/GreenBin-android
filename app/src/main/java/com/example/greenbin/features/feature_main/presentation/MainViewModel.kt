package com.example.greenbin.features.feature_main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.usecase.GetUserNameUseCase
import com.example.domain.categories.model.Category
import com.example.domain.categories.usecase.GetCategoriesUseCase
import com.example.domain.info.usecase.GetInfoCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getInfoCardsUseCase: GetInfoCardsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(isLoading = true))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<MainUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        loadData()
    }

    fun onEvent(event: MainUiEvent) {
        when (event){
            is MainUiEvent.CategoryClicked -> viewModelScope.launch {
                _uiEffect.send(MainUiEffect.Navigate.ToCategoryMap(event.categoryId))
            }
            is MainUiEvent.ProfileClicked -> viewModelScope.launch {
                _uiEffect.send(MainUiEffect.Navigate.ToProfile)
            }
            is MainUiEvent.MapClicked -> viewModelScope.launch {
                _uiEffect.send(MainUiEffect.Navigate.ToGlobalMap)
            }
            is MainUiEvent.LessonsClicked -> viewModelScope.launch {
                _uiEffect.send(MainUiEffect.Navigate.ToLessons)
            }
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            try {
                val userName = getUserNameUseCase()
                val categories: List<Category> = getCategoriesUseCase()
                _uiState.update { it.copy(
                    userName = userName,
                    categories = categories,
                    )
                }
                 getInfoCardsUseCase()
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        _uiState.update { it.copy(error = e.message) }
                    }
                    .collect { infoCards ->
                        _uiState.update {
                            it.copy(
                                infoCards = infoCards,
                                isLoading = false
                            )
                        }
                    }
            }catch (e: Exception){
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }
}

sealed class  MainUiEffect {
    sealed class Navigate : MainUiEffect() {
        data class ToCategoryMap(val categoryId: String) : Navigate()
        data object ToProfile : Navigate()
        data object ToGlobalMap : Navigate()
        data object ToLessons : Navigate()
    }
}