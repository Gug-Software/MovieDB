package gug.co.com.testmovies.repository.movies

import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details.*
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

    fun getDtoMovieDetailForTest(movieId: Int): DtoMovieDetail {

        val genre1 = getDtoGenre(movieId, 1)
        val genre2 = getDtoGenre(movieId, 2)
        val language1 = getDtoSpokenLanguage(movieId, 1)
        val language2 = getDtoSpokenLanguage(movieId, 2)
        val company1 = getDtoProductionCompany(movieId, 1)
        val company2 = getDtoProductionCompany(movieId, 2)

        val dtoMovieDetail = DtoMovieDetail(
            id = movieId,
            adult = false,
            backdropPath = "backdropPath-$movieId",
            originalLanguage = "originalLanguage-$movieId",
            originalTitle = "originalTitle-$movieId",
            overview = "overview-$movieId",
            popularity = 10.0,
            posterPath = "posterPath-$movieId",
            releaseDate = "releaseDate-$movieId",
            title = "title-$movieId",
            video = false,
            voteAverage = 6.0,
            voteCount = 120,
            budget = 0,
            dtoGenres = listOf(genre1, genre2),
            dtoBelongsToCollection = DtoBelongsToCollection("", 1, "", ""),
            dtoProductionCountries = listOf(),
            dtoProductionCompanies = listOf(company1, company2),
            dtoSpokenLanguages = listOf(language1, language2),
            homepage = "homepage$movieId",
            imdbId = "imdbId",
            revenue = 100L,
            runtime = 100,
            tagline = "tagLine$movieId",
            status = "status$movieId"
        )

        return dtoMovieDetail

    }

    fun getDtoGenre(movieId: Int, genreId: Int): DtoGenre {
        val dtoGenre = DtoGenre(
            id = genreId,
            name = "Genre-$genreId"
        )
        return dtoGenre
    }

    fun getDtoSpokenLanguage(movieId: Int, spokenNumber: Int): DtoSpokenLanguage {

        val dtoSpokenLanguage = DtoSpokenLanguage(
            iso6391 = "iso6391-$spokenNumber",
            name = "name-$spokenNumber"
        )

        return dtoSpokenLanguage
    }

    fun getDtoProductionCompany(movieId: Int, companyId: Int): DtoProductionCompany {

        val dbCompany = DtoProductionCompany(
            id = companyId,
            logoPath = "logoPath$companyId",
            name = "name$companyId",
            originCountry = "XX$companyId"
        )

        return dbCompany
    }


}