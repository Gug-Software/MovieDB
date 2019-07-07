package gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details

import com.squareup.moshi.Json

data class DtoSpokenLanguage(

    @Json(name = "iso_639_1")
    val iso6391: String, // ja

    @Json(name = "name")
    val name: String // 日本語

)