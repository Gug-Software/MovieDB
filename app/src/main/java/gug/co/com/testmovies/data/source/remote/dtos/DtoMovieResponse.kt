package gug.co.com.testmovies.data.source.remote.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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