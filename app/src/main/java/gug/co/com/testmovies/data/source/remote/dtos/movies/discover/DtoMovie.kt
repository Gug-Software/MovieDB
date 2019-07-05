package gug.co.com.testmovies.data.source.remote.dtos.movies.discover

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DtoMovie(

    @Json(name = "adult")
    val adult: Boolean, // false

    @Json(name = "backdrop_path")
    val backdropPath: String, // /8sNz2DxYiYqGkxk66U8BqvuZZjE.jpg

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "id")
    val id: Int, // 532321

    @Json(name = "original_language")
    val originalLanguage: String, // ja

    @Json(name = "original_title")
    val originalTitle: String, // Re:ゼロから始める異世界生活 Memory Snow

    @Json(name = "overview")
    val overview: String, // Subaru and friends finally get a moment of peace, ...

    @Json(name = "popularity")
    val popularity: Double, // 78.279

    @Json(name = "poster_path")
    val posterPath: String, // /xqR4ABkFTFYe8NDJi3knwWX7zfn.jpg

    @Json(name = "release_date")
    val releaseDate: String, // 2018-10-06

    @Json(name = "title")
    val title: String, // Re: Zero kara Hajimeru Isekai Seikatsu - Memory Snow

    @Json(name = "video")
    val video: Boolean, // false

    @Json(name = "vote_average")
    val voteAverage: Double, // 5.8

    @Json(name = "vote_count")
    val voteCount: Int // 31

)