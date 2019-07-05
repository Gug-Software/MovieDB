package gug.co.com.testmovies.data.source.remote.dtos.search.movies

import com.squareup.moshi.Json
import gug.co.com.testmovies.data.source.remote.dtos.movies.discover.DtoMovie

data class DtoSearchMoviesResponse(

    @Json(name = "page")
    val page: Int, // 1

    @Json(name = "results")
    val results: List<DtoMovie>,

    @Json(name = "total_pages")
    val totalPages: Int, // 11

    @Json(name = "total_results")
    val totalResults: Int // 215

)