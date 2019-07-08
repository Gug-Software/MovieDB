package gug.co.com.testmovies.data.source.local.room.entities

import androidx.room.Entity
import gug.co.com.testmovies.data.domain.Genre

@Entity(primaryKeys = ["movieId", "id"])
data class DbGenre(
    val movieId: Int,
    val id: Int, // 28
    val name: String // Action
)

fun List<DbGenre>.asDomainModel(): List<Genre> {
    return map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}

