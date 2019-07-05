package gug.co.com.testmovies.data.source.remote.dtos.movies.details


import com.squareup.moshi.Json

data class DtoProductionCompany(

    @Json(name = "id")
    val id: Int, // 420

    @Json(name = "logo_path")
    val logoPath: String, // /hUzeosd33nzE5MCNsZxCGEKTXaQ.png

    @Json(name = "name")
    val name: String, // Marvel Studios

    @Json(name = "origin_country")
    val originCountry: String // US

)