package gug.co.com.testmovies

import gug.co.com.testmovies.data.source.local.MovieDetailLocalDataStore
import gug.co.com.testmovies.data.source.local.room.entities.DbGenre
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.data.source.local.room.entities.DbProductionCompany
import gug.co.com.testmovies.data.source.local.room.entities.DbSpokenLanguage
import gug.co.com.testmovies.utils.Result

class FakeLocalMovieDetailDataSource(
    var movies: MutableList<DbMovie>? = mutableListOf(),
    var genres: MutableList<DbGenre>? = mutableListOf(),
    var companies: MutableList<DbProductionCompany>? = mutableListOf(),
    var languages: MutableList<DbSpokenLanguage>? = mutableListOf()
) :
    MovieDetailLocalDataStore {

    override suspend fun insertGenres(vararg genres: DbGenre) {
        this.genres?.addAll(genres)
    }

    override suspend fun insertProductionCompanies(vararg productionCompanies: DbProductionCompany) {
        this.companies?.addAll(productionCompanies)
    }

    override suspend fun insertSpokenLaguages(vararg spokenLanguages: DbSpokenLanguage) {
        this.languages?.addAll(spokenLanguages)
    }

    override suspend fun getMovieById(movieId: Int): Result<DbMovie> {
        val moviesDb = movies!!.filter { it.id == movieId }
        return Result.Success(moviesDb.get(0))
    }

    override suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>> {
        val genres = genres!!.filter { it.movieId == movieId }
        return Result.Success(genres)
    }

    override suspend fun getSpokenLaguageByMovieId(movieId: Int): Result<List<DbSpokenLanguage>> {
        val languages = languages!!.filter { it.movieId == movieId }
        return Result.Success(languages)
    }

    override suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>> {
        val companies = companies!!.filter { it.movieId == movieId }
        return Result.Success(companies)
    }

    override suspend fun update(movie: DbMovie) {
        movies?.firstOrNull { it.id == movie.id }?.let { it.overview = movie.overview }
    }

}