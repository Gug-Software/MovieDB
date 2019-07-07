package gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details


import com.squareup.moshi.Json

data class DtoGenre(

    @Json(name = "id")
    val id: Int, // 28

    @Json(name = "name")
    val name: String // Action

)