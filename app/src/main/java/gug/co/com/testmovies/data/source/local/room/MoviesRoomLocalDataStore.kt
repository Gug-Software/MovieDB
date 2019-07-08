package gug.co.com.testmovies.data.source.local.room

import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.local.room.dao.MoviesDao
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.Result.Error
import gug.co.com.testmovies.utils.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRoomLocalDataStore(
    private val moviesDao: MoviesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesLocalDataStore {

    override suspend fun getPopularMovies(): Result<List<DbMovie>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(moviesDao.getPopularMovies())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getTopRatedMovies(): Result<List<DbMovie>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(moviesDao.getTopRatedMovies())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getUpComingMovies(): Result<List<DbMovie>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(moviesDao.getUpComingMovies())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun searchPopularMovies(query: String): Result<List<DbMovie>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(moviesDao.searchPopularMovies(query))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun searchTopRatedMovies(query: String): Result<List<DbMovie>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(moviesDao.searchTopRatedMovies(query))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun searchUpComingMovies(query: String): Result<List<DbMovie>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(moviesDao.searchUpComingMovies(query))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun insertAll(vararg movies: DbMovie) = withContext(ioDispatcher) {
        moviesDao.insertAll(*movies)
    }

    override suspend fun update(movie: DbMovie) = withContext(ioDispatcher) {
        moviesDao.update(movie)
    }
}