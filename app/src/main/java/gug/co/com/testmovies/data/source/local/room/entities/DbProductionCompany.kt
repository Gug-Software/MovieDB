package gug.co.com.testmovies.data.source.local.room.entities

import androidx.room.Entity
import gug.co.com.testmovies.data.domain.ProductionCompany

@Entity(primaryKeys = ["movieId", "id"])
data class DbProductionCompany(
    val movieId: Int,
    val id: Int, // 420
    val logoPath: String?, // /hUzeosd33nzE5MCNsZxCGEKTXaQ.png
    val name: String, // Marvel Studios
    val originCountry: String // US
)

fun List<DbProductionCompany>.asDomainModel(): List<ProductionCompany> {
    return map {
        ProductionCompany(
            id = it.id,
            logoPath = it.logoPath,
            originCountry = it.originCountry,
            name = it.name
        )
    }
}

