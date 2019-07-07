package gug.co.com.testmovies

import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.Result

class FakeLocalDataSource(var movies: MutableList<DbMovie>? = mutableListOf()) :
    MoviesLocalDataStore {

    override suspend fun getPopularMovies(): Result<List<DbMovie>> {
        val moviesDb = movies!!.filter { it.isPopular == true }
        return Result.Success(moviesDb)
    }

    override suspend fun getTopRatedMovies(): Result<List<DbMovie>> {
        val moviesDb = movies!!.filter { it.isTopRated == true }
        return Result.Success(moviesDb)
    }

    override suspend fun getUpComingMovies(): Result<List<DbMovie>> {
        val moviesDb = movies!!.filter { it.isUpComing == true }
        return Result.Success(moviesDb)
    }

    override suspend fun insertAll(vararg movies: DbMovie) {
        this.movies?.addAll(movies)
    }

    override suspend fun update(movie: DbMovie) {
        movies?.firstOrNull { it.id == movie.id }?.let { it.overview = movie.overview }
    }

}