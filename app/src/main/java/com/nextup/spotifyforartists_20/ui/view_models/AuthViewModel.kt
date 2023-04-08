package com.nextup.spotifyforartists_20.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextup.spotifyforartists_20.data.models.artist.Artist
import com.nextup.spotifyforartists_20.data.models.auth.LoginParams
import com.nextup.spotifyforartists_20.data.models.auth.RegisterParams
import com.nextup.spotifyforartists_20.data.repository.auth.AuthRepository
import com.nextup.spotifyforartists_20.domain.android.UiState
import com.nextup.spotifyforartists_20.domain.firebase.ArtistIdProvider
import com.nextup.spotifyforartists_20.domain.network.ApiResponse
import com.nextup.spotifyforartists_20.domain.network.NetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val networkConnection: NetworkConnection,
) : ViewModel() {


    private val _registerState = MutableStateFlow<UiState<Boolean>>(UiState.Idle())
    val registerState = _registerState.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<Artist>>(UiState.Idle())
    val loginState = _loginState.asStateFlow()

    fun register(registerParams: RegisterParams) = viewModelScope.launch {
        _registerState.emit(UiState.Loading())

        if (networkConnection.check()){

            when(val response = authRepository.register(registerParams)){
                is ApiResponse.ApiFailed -> {
                    _registerState.emit(UiState.Error(data = response.data, response.message.toString()))
                }
                is ApiResponse.ApiSuccess -> {
                    _registerState.emit(UiState.Success(response.data, message = "You have been successfully become an artist"))
                }
            }
        }else{
            _registerState.emit(UiState.Error(data = false, "Please turn on your internet connection"))
        }
    }

    fun login(loginParams: LoginParams) = viewModelScope.launch {
        _loginState.emit(UiState.Loading())
        if (networkConnection.check()){

            when(val response = authRepository.login(loginParams)){
                is ApiResponse.ApiFailed -> {
                    _loginState.emit(UiState.Error(data = response.data, response.message.toString()))
                }
                is ApiResponse.ApiSuccess -> {
                    _loginState.emit(UiState.Success(response.data, message = "You have been successfully become an artist"))
                }
            }
        }else{
            _loginState.emit(UiState.Error(message = "Please turn on your internet connection"))
        }
    }



}