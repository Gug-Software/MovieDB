package gug.co.com.testmovies.repository.movies

import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovie

class RemoteDtosTestUtils {

    fun getDtoMovieForTest(i: Int): DtoMovie {

        val dtoMovie = DtoMovie(
            id = i,
            adult = false,
            backdropPath = "backdropPath-$i",
            originalLanguage = "originalLanguage-$i",
            originalTitle = "originalTitle-$i",
            overview = "overview-$i",
            popularity = 10.0,
            posterPath = "posterPath-$i",
            releaseDate = "releaseDate-$i",
            title = "title-$i",
            video = false,
            voteAverage = 6.0,
            voteCount = 120,
            genreIds = listOf()
        )

        return dtoMovie
    }


}