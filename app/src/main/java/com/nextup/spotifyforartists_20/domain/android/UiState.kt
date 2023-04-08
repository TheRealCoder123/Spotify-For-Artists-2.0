package com.nextup.spotifyforartists_20.domain.android

sealed class UiState <T> (val data: T? = null, val message: String? = null, val navigateTo: String? = null){

    class Idle<T>() : UiState<T>()
    class Loading<T>(data: T? = null, message: String? = null) : UiState<T>(data, message)
    class Success<T>(data: T?, message: String? = null, navigateTo: String? = null) : UiState<T>(data, message, navigateTo)
    class Error<T>(data: T? = null, message: String, navigateTo: String? = null) : UiState<T>(data, message, navigateTo)

}
