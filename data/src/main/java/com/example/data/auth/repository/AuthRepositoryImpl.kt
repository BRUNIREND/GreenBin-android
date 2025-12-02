package com.example.data.auth.repository

import com.example.domain.auth.model.User
import com.example.domain.auth.repository.IAuthRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): IAuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            Tasks.await(firebaseAuth.signInWithEmailAndPassword(email, password))
            Result.success(Unit)
        } catch (e: FirebaseAuthException){
            Result.failure(e)
        }
    }


    override suspend fun register(email: String, password: String): Result<User> {
        return try {
            val result = Tasks.await(firebaseAuth.createUserWithEmailAndPassword(email, password))
            val user = result.user?.let { User(id = it.uid, email = it.email ?: "") }
                ?: return Result.failure(IllegalStateException("User not created"))

            Result.success(user)
        } catch (e: FirebaseAuthException) {
            Result.failure(e)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}