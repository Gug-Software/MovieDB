package gug.co.com.testmovies.data.domain

import gug.co.com.testmovies.data.source.remote.retrofit.IMAGE_PATH

data class MovieDetail(

    val id: Int,
    val originalTitle: String = "",
    val backdropPath: String = "",
    val posterPath: String = "",
    val voteAverage: Double = 0.0,
    val overview: String

) {

    val definitivePosterPath: String = getDefinitivePosterPath(posterPath)
    val definitiveBackdropPath: String = getDefinitivePosterPath(backdropPath)
    val average = voteAverage.toString()

    private fun getDefinitivePosterPath(posterPath: String): String {
        return "$IMAGE_PATH$posterPath"
    }

}