package com.nextup.spotifyforartists_20.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.nextup.spotifyforartists_20.data.models.artist.Artist
import com.nextup.spotifyforartists_20.data.models.auth.LoginParams
import com.nextup.spotifyforartists_20.data.models.auth.RegisterParams
import com.nextup.spotifyforartists_20.domain.firebase.ArtistIdProvider
import com.nextup.spotifyforartists_20.domain.firebase.Collections
import com.nextup.spotifyforartists_20.domain.network.ApiResponse
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : IAuthRepository {
    override suspend fun login(params: LoginParams): ApiResponse<Artist> {
        return try {
            val loginResult = firebaseAuth.signInWithEmailAndPassword(params.email, params.password).await()
            val artistId = "${loginResult.user!!.uid}-artist"
            val user = firestore.collection(Collections.ARTISTS.name).document(artistId)
                .get().await().toObject(Artist::class.java)

            if (user != null){
               return ApiResponse.ApiSuccess(user, "Successfully Logged in")
            }

            signOut()
            ApiResponse.ApiFailed(message = "This account is not registered as an artist")
        }catch (e: Exception){
            ApiResponse.ApiFailed(message = e.localizedMessage ?: "Your credentials doesn't match with any in our database")
        }
    }

    override suspend fun register(registerParams: RegisterParams): ApiResponse<Boolean> {
        return try {

            val registerResponse = firebaseAuth.createUserWithEmailAndPassword(registerParams.email, registerParams.password).await()
            val artistId = "${registerResponse.user!!.uid}-artist"


            val artist = Artist(
                registerParams.name,
                registerParams.lastname,
                registerParams.email,
                artistId = artistId
            )

            firestore.collection(Collections.ARTISTS.name).document(artistId)
                .set(artist, SetOptions.merge())
                .await()

            signOut()

            ApiResponse.ApiSuccess(data = true, message = "You have been successfully registered")
        }catch (e: Exception){
            ApiResponse.ApiFailed(message = e.localizedMessage!!)
        }
    }

    override suspend fun signOut(): ApiResponse<String> {
        return try {
            firebaseAuth.signOut()
            ApiResponse.ApiSuccess(data = "Signed Out", null)
        }catch (e: Exception){
            ApiResponse.ApiFailed(message = e.message.toString())
        }
    }
}