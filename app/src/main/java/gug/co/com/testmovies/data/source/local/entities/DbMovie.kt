package gug.co.com.testmovies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import gug.co.com.testmovies.data.source.remote.dtos.movies.details.*

@Entity
data class DbMovie(

    @PrimaryKey
    val id: Int, // 299534

    val adult: Boolean, // false
    val backdropPath: String, // /7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg
    val budget: Int, // 356000000
    val homepage: String, // https://www.marvel.com/movies/avengers-endgame
    val imdbId: String, // tt4154796
    val originalLanguage: String, // en
    val originalTitle: String, // Avengers: Endgame
    val overview: String, // After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.
    val popularity: Double, // 93.133
    val posterPath: String, // /or06FN3Dka5tukK1e9sl16pB3iy.jpg
    val releaseDate: String, // 2019-04-24
    val revenue: Long, // 2764881856
    val runtime: Int, // 181
    val status: String, // Released
    val tagline: String, // Part of the journey is the end.
    val title: String, // Avengers: Endgame
    val video: Boolean, // false
    val voteAverage: Double, // 8.4
    val voteCount: Int, // 7421

    val isPopular: Boolean,
    val isTopRated: Boolean,
    val isUpComing: Boolean

)