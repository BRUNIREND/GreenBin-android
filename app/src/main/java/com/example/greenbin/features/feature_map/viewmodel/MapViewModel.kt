package com.example.greenbin.features.feature_map.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.R
import com.example.domain.categories.model.Category
import com.example.domain.map.model.RecyclingPoint
import com.example.domain.map.usecase.GetPointsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getPointsByCategoryUseCase: GetPointsByCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val categories = listOf(
        Category("plastic", "Пластик", R.drawable.ic_plastic),
        Category("glass", "Стекло", R.drawable.ic_glass),
        Category("batteries", "Батарейки", R.drawable.ic_battery),
        Category("paper", "Бумага", R.drawable.ic_paper),
        Category("metal", "Металл", R.drawable.ic_metal),
        // ... остальные
    )

    private val initialCategoryId: String? = savedStateHandle["categoryId"]
    private val _selectedCategory = MutableStateFlow<String?>(initialCategoryId)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _selectedPoint = MutableStateFlow<RecyclingPoint?>(null)
    val selectedPoint: StateFlow<RecyclingPoint?> = _selectedPoint.asStateFlow()

    val points = selectedCategory.flatMapLatest { categoryId ->
        flow {
            emit(getPointsByCategoryUseCase(categoryId))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    fun onCategorySelected(categoryId: String?) {
        // Если кликнули на уже выбранную — снимаем фильтр
        _selectedCategory.value = if (_selectedCategory.value == categoryId) null else categoryId
    }
    var searchQuery by mutableStateOf("")
        private set



    fun selectPoint(point: RecyclingPoint) {
        _selectedPoint.value = point
    }

    fun clearSelectedPoint() {
        _selectedPoint.value = null
    }


    fun updateSearchQuery(query: String) {
        searchQuery = query
        // TODO: фильтрация пунктов по поиску
    }

}