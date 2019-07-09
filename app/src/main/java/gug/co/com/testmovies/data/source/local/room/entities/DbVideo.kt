package gug.co.com.testmovies.data.source.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import gug.co.com.testmovies.data.domain.MovieVideo

@Entity
data class DbVideo(

    @PrimaryKey
    val id: String, // 5d1461950a517c00305ec236

    val movieId: Int,

    val iso31661: String, // US
    val iso6391: String, // en
    val key: String, // w-lUE5egBqs
    val name: String, // Marvel Studios' Avengers: Endgame | On Digital 7/30 & Blu-ray 8/13
    val site: String, // YouTube
    val size: Int, // 1080
    val type: String // Featurette

)

fun List<DbVideo>.asDomainModel(): List<MovieVideo> {
    return map {
        MovieVideo(
            key = it.key,
            name = it.name,
            site = it.site,
            type = it.site
        )
    }
}