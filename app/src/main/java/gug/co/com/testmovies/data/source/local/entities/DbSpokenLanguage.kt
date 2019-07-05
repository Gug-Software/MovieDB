package gug.co.com.testmovies.data.source.local.entities

import androidx.room.Entity

@Entity
data class DbSpokenLanguage(
    val movieId: Int,
    val iso6391: String, // ja
    val name: String // 日本語
)