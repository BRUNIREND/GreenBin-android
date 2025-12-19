package com.example.data.auth.repository

import com.example.data.auth.di.IoDispatcher
import com.example.domain.info.model.InfoCard
import com.example.domain.info.repository.IInfoCardRepository
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

@Singleton
class InfoCardRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher   // лучше CoroutineDispatcher, а не CoroutineContext
) : IInfoCardRepository {

    override fun getInfoCards(): Flow<List<InfoCard>> = callbackFlow {
        val listener = firestore.collection("info_cards")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)           // пробрасываем ошибку в Flow
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val cards = snapshot.documents.mapNotNull { doc ->
                        InfoCard(
                            id = doc.id,
                            title = doc.getString("title") ?: "",
                            description = doc.getString("description") ?: "",
                            categoryId = doc.getString("categoryId")
                        )
                    }
                    trySend(cards).isSuccess
                }
            }

        // Отписываемся при отмене Flow
        awaitClose { listener.remove() }
    }
        .flowOn(ioDispatcher)        // весь тяжёлый код в IO
        .distinctUntilChanged()     // не шлём одинаковые списки
        .conflate()                  // если приходит быстрее, чем успеваем обработать
}