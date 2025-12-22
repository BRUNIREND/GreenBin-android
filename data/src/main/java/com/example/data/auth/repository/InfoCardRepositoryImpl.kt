package com.example.data.auth.repository

import com.example.data.R
import com.example.data.auth.di.IoDispatcher
import com.example.domain.info.model.InfoCard
import com.example.domain.info.repository.IInfoCardRepository
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class InfoCardRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher   // лучше CoroutineDispatcher, а не CoroutineContext
) : IInfoCardRepository {
    override fun getInfoCards(): Flow<List<InfoCard>> = flow {
        val hardcodedCards = listOf(
            InfoCard(
                id = "1",
                title = "Маркировка",
                description = "Справочник кодов переработки отходов",
//                imageRes = R.drawable.ic_marking  // локальная иконка
                imageRes = R.drawable.ic_marking,  // локальная иконка,
                categoryId = null
            ),
            InfoCard(
                id = "2",
                title = "Сортировка",
                description = "Как правильно сортировать мусор дома",
//                imageRes = R.drawable.ic_sorting
                imageRes = R.drawable.ic_marking,
                categoryId = null

            ),
            InfoCard(
                id = "3",
                title = "Утилизация",
                description = "Куда сдавать опасные отходы",
                imageRes = R.drawable.ic_marking,
                categoryId = null

            ),
            InfoCard(
                id = "4",
                title = "Эко-привычки",
                description = "Простые шаги к экологичному образу жизни",
                imageRes = R.drawable.ic_marking,
                categoryId = null
            )
            // Добавь сколько угодно карточек
        )
        emit(hardcodedCards)
    }
//    override fun getInfoCards(): Flow<List<InfoCard>> = callbackFlow {
//        val listener = firestore.collection("info_cards")
//            .addSnapshotListener { snapshot, error ->
//                if (error != null) {
//                    close(error)           // пробрасываем ошибку в Flow
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    val cards = snapshot.documents.mapNotNull { doc ->
//                        InfoCard(
//                            id = doc.id,
//                            title = doc.getString("title") ?: "",
//                            description = doc.getString("description") ?: "",
//                            categoryId = doc.getString("categoryId")
//                        )
//                    }
//                    trySend(cards).isSuccess
//                }
//            }
//
//        // Отписываемся при отмене Flow
//        awaitClose { listener.remove() }
//    }
//        .flowOn(ioDispatcher)        // весь тяжёлый код в IO
//        .distinctUntilChanged()     // не шлём одинаковые списки
//        .conflate()                  // если приходит быстрее, чем успеваем обработать
}