package com.example.domain.info.repository

import com.example.domain.info.model.InfoCard
import kotlinx.coroutines.flow.Flow

interface IInfoCardRepository {
    fun getInfoCards(): Flow<List<InfoCard>>  // Realtime с Flow
}