package gug.co.com.testmovies.data.source.local.entities

import androidx.room.Entity

@Entity
data class DbProductionCompany(
    val movieId: Int,
    val id: Int, // 420
    val logoPath: String, // /hUzeosd33nzE5MCNsZxCGEKTXaQ.png
    val name: String, // Marvel Studios
    val originCountry: String // US
)