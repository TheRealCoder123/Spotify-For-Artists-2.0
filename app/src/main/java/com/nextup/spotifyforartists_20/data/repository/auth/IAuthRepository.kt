package com.nextup.spotifyforartists_20.data.repository.auth

import com.nextup.spotifyforartists_20.data.models.artist.Artist
import com.nextup.spotifyforartists_20.data.models.auth.LoginParams
import com.nextup.spotifyforartists_20.data.models.auth.RegisterParams
import com.nextup.spotifyforartists_20.domain.network.ApiResponse

interface IAuthRepository {

    suspend fun login(params: LoginParams): ApiResponse<Artist>
    suspend fun register(registerParams: RegisterParams): ApiResponse<Boolean>
    suspend fun signOut(): ApiResponse<String>



}