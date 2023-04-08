package com.nextup.spotifyforartists_20.domain.network


sealed class ApiResponse <T> (val data: T? = null, val message: String? = null) {

    class ApiSuccess<T>(data: T?, message: String?) : ApiResponse<T>(data = data, message = message)
    class ApiFailed<T>(message: String) : ApiResponse<T>(message = message)


}
