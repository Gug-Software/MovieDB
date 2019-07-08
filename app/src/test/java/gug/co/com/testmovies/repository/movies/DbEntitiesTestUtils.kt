package gug.co.com.testmovies.repository.movies


import gug.co.com.testmovies.data.source.local.room.entities.DbGenre
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.data.source.local.room.entities.DbProductionCompany
import gug.co.com.testmovies.data.source.local.room.entities.DbSpokenLanguage
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

    fun getDbGenre(movieId: Int, genreId: Int): DbGenre {
        val dbGenre = DbGenre(
            movieId = movieId,
            id = 1,
            name = "Genre-$genreId"
        )
        return dbGenre
    }

    fun getDbSpokenLanguage(movieId: Int, spokenNumber: Int): DbSpokenLanguage {

        val dbSpokenLanguage = DbSpokenLanguage(
            movieId = movieId,
            iso6391 = "iso6391-$spokenNumber",
            name = "name-$spokenNumber"
        )
        return dbSpokenLanguage
    }

    fun getDbProductionCompany(movieId: Int, companyId: Int): DbProductionCompany {

        val dbCompany = DbProductionCompany(
            movieId = movieId,
            id = companyId,
            logoPath = "logoPath$companyId",
            name = "name$companyId",
            originCountry = "XX$companyId"
        )
        return dbCompany
    }


}