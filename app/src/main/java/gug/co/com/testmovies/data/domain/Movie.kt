package gug.co.com.testmovies.data.domain

import gug.co.com.testmovies.data.source.remote.retrofit.IMAGE_PATH

data class Movie(

    val id: Int,
    val originalTitle: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double
) {

    var definitivePosterPath: String = getDefinitivePosterPath(posterPath)
    var definitiveBackdropPath: String = getDefinitivePosterPath(backdropPath)

    private fun getDefinitivePosterPath(posterPath: String): String {
        return "$IMAGE_PATH$posterPath"
    }
}

