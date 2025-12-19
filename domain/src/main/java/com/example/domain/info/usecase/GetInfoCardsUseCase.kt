package com.example.domain.info.usecase

import com.example.domain.info.model.InfoCard
import com.example.domain.info.repository.IInfoCardRepository
import kotlinx.coroutines.flow.Flow

class GetInfoCardsUseCase (
    private val repository: IInfoCardRepository
){
    operator fun invoke(): Flow<List<InfoCard>> = repository.getInfoCards()

}