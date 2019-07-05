package gug.co.com.testmovies.data.source.remote.dtos.movies.videos

import com.squareup.moshi.Json
import gug.co.com.testmovies.data.source.local.entities.DbVideo

data class DtoMovieVideosResponse(

    @Json(name = "id")
    val id: Int, // 299534

    @Json(name = "results")
    val results: List<DtoMovieVideo>

)

fun List<DtoMovieVideo>.asDatabaseModel(movieId: Int): Array<DbVideo> {

    return map {
        DbVideo(
            movieId = movieId,
            id = it.id,
            iso31661 = it.iso31661,
            iso6391 = it.iso6391,
            key = it.key,
            name = it.name,
            site = it.site,
            size = it.size,
            type = it.type
        )
    }.toTypedArray()

}