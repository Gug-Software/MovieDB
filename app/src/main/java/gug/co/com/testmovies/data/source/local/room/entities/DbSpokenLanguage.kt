package gug.co.com.testmovies.data.source.local.room.entities

import androidx.room.Entity
import gug.co.com.testmovies.data.domain.SpokenLanguage

@Entity(primaryKeys = ["movieId", "iso6391"])
data class DbSpokenLanguage(
    val movieId: Int,
    val iso6391: String, // ja
    val name: String // 日本語
)

fun List<DbSpokenLanguage>.asDomainModel(): List<SpokenLanguage> {
    return map {
        SpokenLanguage(
            iso6391 = it.iso6391,
            name = it.name
        )
    }
}