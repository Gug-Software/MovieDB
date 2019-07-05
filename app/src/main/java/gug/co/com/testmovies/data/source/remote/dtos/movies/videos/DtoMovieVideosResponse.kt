package gug.co.com.testmovies.data.source.remote.dtos.movies.videos


import com.squareup.moshi.Json

data class DtoMovieVideosResponse(

    @Json(name = "id")
    val id: Int, // 299534

    @Json(name = "results")
    val results: List<DtoMovieVideo>

)