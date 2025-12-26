package com.example.greenbin.features.feature_map.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.map.usecase.GetPointByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class PointDetailViewModel @Inject constructor(
    private val getPointByIdUseCase: GetPointByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pointId: String = savedStateHandle["pointId"]!!

    val point = flow {
        emit(getPointByIdUseCase(pointId))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}