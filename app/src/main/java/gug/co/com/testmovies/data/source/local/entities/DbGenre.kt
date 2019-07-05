package gug.co.com.testmovies.data.source.local.entities

import androidx.room.Entity

@Entity
data class DbGenre(
    val movieId: Int,
    val id: Int, // 28
    val name: String // Action
)