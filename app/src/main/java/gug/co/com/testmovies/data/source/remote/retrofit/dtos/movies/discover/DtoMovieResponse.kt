package gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.movies.MoviesFilter

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

fun List<DtoMovie>.asDatabaseModel(moviesFilter: MoviesFilter?): Array<DbMovie> {

    return map {
        val dbMovie = DbMovie(
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

        if (moviesFilter != null) {
            when (moviesFilter) {
                MoviesFilter.POPULAR -> dbMovie.isPopular = true
                MoviesFilter.TOP_RATED -> dbMovie.isTopRated = true
                MoviesFilter.UP_COMING -> dbMovie.isUpComing = true
                MoviesFilter.GLOBAL -> dbMovie.isUpComing = false
            }
        }

        dbMovie
    }.toTypedArray()

}