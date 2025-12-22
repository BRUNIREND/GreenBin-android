package com.example.data.auth.repository

import com.example.data.R
import com.example.domain.banner.model.Banner
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
        Banner("Основы сортировки", "Как правильно сортировать отходы", R.drawable.banner_sorting),
        Banner("Виды пластика", "Какой пластик подлежит переработке", R.drawable.banner_sorting_types),
        Banner("Виды пластика", "Какой пластик подлежит переработке", R.drawable.banner_sorting_types)
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