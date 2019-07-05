package gug.co.com.testmovies.data.source.remote.dtos.movies.details

import com.squareup.moshi.Json

data class DtoProductionCountry(

    @Json(name = "iso_3166_1")
    val iso31661: String, // US

    @Json(name = "name")
    val name: String // United States of America

)