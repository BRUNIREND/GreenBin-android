package com.example.data.auth.repository

import com.example.domain.auth.model.User
import com.example.domain.auth.repository.IAuthRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): IAuthRepository {

    override val currentUser: User?
        get() = firebaseAuth.currentUser?.let { fbUser ->
            User(
                uid = fbUser.uid,
                email = fbUser.email,
                displayName = fbUser.displayName
            )
        }

    override suspend fun getUserProfile(uid: String): User? {
        val doc = firestore.collection("users").document(uid).get().await()
        return if (doc.exists()) {
            User(
                uid = uid,
                email = doc.getString("email"),
                displayName = doc.getString("name") // displayName
            )
        } else null
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            withContext(Dispatchers.IO){
                Tasks.await(firebaseAuth.signInWithEmailAndPassword(email, password))
            }
            Result.success(Unit)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(e)   // ← это важная ошибка — неправильный логин/пароль
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun register(email: String, password: String): Result<User> {
        return try {
            val result = withContext(Dispatchers.IO){
                Tasks.await(firebaseAuth.createUserWithEmailAndPassword(email, password))
            }
            val user = result.user?.let { User(uid = it.uid, email = it.email ?: "", displayName = it.displayName ?: "") }
                ?: return Result.failure(IllegalStateException("User not created"))

            Result.success(user)
        } catch (e: FirebaseAuthException) {
            Result.failure(e)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun saveUserProfile(user: User): Result<Unit> = try {
        withContext(Dispatchers.IO){
            firestore.collection("users")
                .document(user.uid)
                .set(mapOf(
                    "name" to user.displayName,
                    "email" to user.email,
                    "phone" to user.phone,
                    "address" to user.address
                )).await()
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun logout(): Result<Unit> = try {
        firebaseAuth.signOut()
        Result.success(Unit)
    } catch (e: Exception){
        Result.failure(e)
    }
}