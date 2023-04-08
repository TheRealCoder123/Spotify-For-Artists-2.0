package com.nextup.spotifyforartists_20.data.models.artist

data class Artist(
    val name: String = "",
    val lastname: String = "",
    val email: String = "",
    val songs: Int = 0,
    val albums: Int = 0,
    val streams: Int = 0,
    val artistId: String = "",
)
