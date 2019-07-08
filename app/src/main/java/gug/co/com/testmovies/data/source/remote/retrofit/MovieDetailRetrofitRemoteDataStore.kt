package gug.co.com.testmovies.data.source.remote.retrofit

import gug.co.com.testmovies.data.source.remote.MovieDetailRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details.DtoMovieDetail
import gug.co.com.testmovies.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailRetrofitRemoteDataStore(
    private val moviesAPI: MoviesAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDetailRemoteDataStore {

    override suspend fun getMovieDetail(movieId: Int): Result<DtoMovieDetail> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                moviesAPI.getMovieDetail(
                    movieId, API_KEY, LANGUAGE_ES
                ).await()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}