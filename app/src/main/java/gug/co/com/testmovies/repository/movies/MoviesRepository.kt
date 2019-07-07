package gug.co.com.testmovies.repository.movies

import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovieResponse
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.asDatabaseModel
import gug.co.com.testmovies.ui.movies.IContractMovies
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val moviesRemoteDataStore: MoviesRemoteDataStore,
    private val moviesLocalDataStore: MoviesLocalDataStore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractMovies.Model {

    override suspend fun getMoviesByFilter(moviesFilter: MoviesFilter): Result<List<DbMovie>> {

        return withContext(ioDispatcher) {

            val moviesToShow = fetchMoviesFromLocalOrRemote(moviesFilter)
            (moviesToShow as? Result.Success)?.let {
                return@withContext Result.Success(it.data)
            }

            return@withContext Result.Error(Exception("Illegal state"))
        }


    }

    private suspend fun fetchMoviesFromLocalOrRemote(
        moviesFilter: MoviesFilter
    ): Result<List<DbMovie>> {

        val moviesDb = fetchMoviesFromLocal(moviesFilter)
        (moviesDb as Result.Success)?.let {

            // if the data is empty we need to consult in remote data store
            if (it.data.isEmpty()) {
                val moviesRemote = fetchMoviesFromRemote(moviesFilter)
                (moviesRemote as Result.Success)?.let { (data) ->
                    // save the movies from remote in local store
                    moviesLocalDataStore.insertAll(*data.results.asDatabaseModel(moviesFilter))
                }
            } else {
                return moviesDb
            }
        }

        return fetchMoviesFromLocal(moviesFilter)

    }

    private suspend fun fetchMoviesFromLocal(moviesFilter: MoviesFilter): Result<List<DbMovie>> {

        return when (moviesFilter) {
            MoviesFilter.POPULAR -> moviesLocalDataStore.getPopularMovies()
            MoviesFilter.TOP_RATED -> moviesLocalDataStore.getTopRatedMovies()
            MoviesFilter.UP_COMING -> moviesLocalDataStore.getUpComingMovies()
            else -> moviesLocalDataStore.getPopularMovies()
        }
    }

    private suspend fun fetchMoviesFromRemote(moviesFilter: MoviesFilter): Result<DtoMovieResponse> {

        return when (moviesFilter) {
            MoviesFilter.POPULAR -> moviesRemoteDataStore.getPopularMovies()
            MoviesFilter.TOP_RATED -> moviesRemoteDataStore.getTopRatedMovies()
            MoviesFilter.UP_COMING -> moviesRemoteDataStore.getUpComingMovies()
            else -> moviesRemoteDataStore.getPopularMovies()
        }


    }

}