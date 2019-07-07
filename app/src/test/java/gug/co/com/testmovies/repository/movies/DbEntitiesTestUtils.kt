package gug.co.com.testmovies.repository.movies

import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.movies.MoviesFilter

class DbEntitiesTestUtils {

    fun getDbMovieForTest(i: Int, movieFilter: MoviesFilter?): DbMovie {

        val dbMovie = DbMovie(
            id = i,
            adult = false,
            backdropPath = "backdropPath-$i",
            budget = 0,
            homepage = "homepage-$i",
            imdbId = "imdbId-$i",
            originalLanguage = "originalLanguage-$i",
            originalTitle = "originalTitle-$i",
            overview = "overview-$i",
            popularity = 10.0,
            posterPath = "posterPath-$i",
            releaseDate = "releaseDate-$i",
            revenue = 0L,
            runtime = 0,
            status = "status-$i",
            tagline = "tagline-$i",
            title = "title-$i",
            video = false,
            voteAverage = 6.0,
            voteCount = 120,
            isPopular = false,
            isTopRated = false,
            isUpComing = false
        )

        if (movieFilter != null) {
            when (movieFilter) {
                MoviesFilter.POPULAR -> dbMovie.isPopular = true
                MoviesFilter.TOP_RATED -> dbMovie.isTopRated = true
                MoviesFilter.UP_COMING -> dbMovie.isUpComing = true
            }
        }

        return dbMovie
    }


}