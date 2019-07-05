package gug.co.com.testmovies.data.source.local.entities

import androidx.room.Entity

@Entity
data class DbVideo(
    
    val movieId: Int,
    val id: String, // 5d1461950a517c00305ec236
    val iso31661: String, // US
    val iso6391: String, // en
    val key: String, // w-lUE5egBqs
    val name: String, // Marvel Studios' Avengers: Endgame | On Digital 7/30 & Blu-ray 8/13
    val site: String, // YouTube
    val size: Int, // 1080
    val type: String // Featurette

)