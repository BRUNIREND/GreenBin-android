package com.example.data.auth.repository

import com.example.data.R
import com.example.domain.banner.model.Banner
import com.example.domain.banner.model.StoryCard
import com.example.domain.banner.repository.IBannerRepository
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class BannerRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
//    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IBannerRepository {

    override fun getBanners(): List<Banner> = listOf(
        Banner(
            "1", "Эко-день в городе", "Присоединяйся к акции!", R.drawable.banner_sorting,
            cards = listOf(
                StoryCard("1-1", "Шаг 1", "Собери мусор в парке", R.drawable.banner_sorting),
                StoryCard("1-2", "Шаг 2", "Сдай в пункт переработки", R.drawable.banner_sorting),
                StoryCard("1-3", "Шаг 3", "Получи бонус", R.drawable.banner_sorting)
            )
        ),
        Banner(
            "2", "Зелёный вызов", "Выиграй призы!", R.drawable.banner_sorting_types,
            cards = listOf(
                StoryCard("2-1", "Задание 1", "Сдай 10 бутылок", R.drawable.banner_sorting_types),
                StoryCard("2-2", "Задание 2", "Расскажи друзьям", R.drawable.banner_sorting_types)
            )
        ),
        Banner(
            "3", "Зелёный вызов", "Выиграй призы!", R.drawable.banner_sorting,
            cards = listOf(
                StoryCard("2-1", "Задание 1", "Сдай 10 бутылок", R.drawable.banner_sorting_types),
                StoryCard("2-2", "Задание 2", "Расскажи друзьям", R.drawable.banner_sorting_types)
            )
        )
    )

//    override fun getBanners(): Flow<List<Banner>> = callbackFlow {
//        val listener = firestore.collection("banners")
//            .addSnapshotListener { snapshot, error ->
//                if (error != null) {
//                    close(error)
//                    return@addSnapshotListener
//                }
//                snapshot?.let {
//                    val banners = it.documents.mapNotNull { doc ->
//                        Banner(
//                            id = doc.id,
//                            title = doc.getString("title") ?: "",
//                            subtitle = doc.getString("subtitle") ?: "",
//                            imageUrl = doc.getString("imageUrl") ?: ""
//                        )
//                    }
//                    trySend(banners)
//                }
//            }
//        awaitClose { listener.remove() }
//    }.flowOn(ioDispatcher)
}