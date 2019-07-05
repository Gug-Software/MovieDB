package gug.co.com.testmovies.data.source.remote.dtos.movies.videos

import com.squareup.moshi.Json

data class DtoMovieVideo(

    @Json(name = "id")
    val id: String, // 5d1461950a517c00305ec236

    @Json(name = "iso_3166_1")
    val iso31661: String, // US

    @Json(name = "iso_639_1")
    val iso6391: String, // en

    @Json(name = "key")
    val key: String, // w-lUE5egBqs

    @Json(name = "name")
    val name: String, // Marvel Studios' Avengers: Endgame | On Digital 7/30 & Blu-ray 8/13

    @Json(name = "site")
    val site: String, // YouTube

    @Json(name = "size")
    val size: Int, // 1080

    @Json(name = "type")
    val type: String // Featurette

)