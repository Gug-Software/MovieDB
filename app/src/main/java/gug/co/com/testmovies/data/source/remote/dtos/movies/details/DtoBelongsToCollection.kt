package gug.co.com.testmovies.data.source.remote.dtos.movies.details

import com.squareup.moshi.Json

data class DtoBelongsToCollection(

    @Json(name = "backdrop_path")
    val backdropPath: String, // /zuW6fOiusv4X9nnW3paHGfXcSll.jpg

    @Json(name = "id")
    val id: Int, // 86311

    @Json(name = "name")
    val name: String, // The Avengers Collection

    @Json(name = "poster_path")
    val posterPath: String // /yFSIUVTCvgYrpalUktulvk3Gi5Y.jpg

)