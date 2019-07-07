package gug.co.com.testmovies.data.source.local.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "iso6391"])
data class DbSpokenLanguage(
    val movieId: Int,
    val iso6391: String, // ja
    val name: String // 日本語
)