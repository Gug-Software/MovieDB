package gug.co.com.testmovies.data.source.remote.dtos.movies.discover

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gug.co.com.testmovies.data.source.local.entities.DbMovie

@JsonClass(generateAdapter = true)
data class DtoMovieResponse(

    @Json(name = "page")
    val page: Int, // 1

    @Json(name = "results")
    val results: List<DtoMovie>,

    @Json(name = "total_pages")
    val totalPages: Int, // 21200

    @Json(name = "total_results")
    val totalResults: Int // 423999

)

fun List<DtoMovie>.asDatabaseModel(): Array<DbMovie> {

    return map {
        DbMovie(
            id = it.id,
            adult = it.adult,
            backdropPath = it.backdropPath,
            budget = 0,
            homepage = "",
            imdbId = "",
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            revenue = 0L,
            runtime = 0,
            status = "",
            tagline = "",
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            isPopular = false,
            isTopRated = false,
            isUpComing = false
        )
    }.toTypedArray()

}