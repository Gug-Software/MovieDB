package gug.co.com.testmovies.data.source.remote.retrofit

import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovieResponse
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.search.movies.DtoSearchMoviesResponse
import gug.co.com.testmovies.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRetrofitRemoteDataStore(
    private val moviesAPI: MoviesAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRemoteDataStore {

    override suspend fun getPopularMovies(): Result<DtoMovieResponse> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                moviesAPI.getPopularMovies(
                    API_KEY, LANGUAGE_ES, REGION_COLOMBIA
                ).await()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTopRatedMovies(): Result<DtoMovieResponse> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                moviesAPI.getTopRatedMovies(
                    API_KEY, LANGUAGE_ES, REGION_COLOMBIA
                ).await()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUpComingMovies(): Result<DtoMovieResponse> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                moviesAPI.getUpComingMovies(
                    API_KEY, LANGUAGE_ES, REGION_COLOMBIA
                ).await()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun searchMovies(query: String): Result<DtoSearchMoviesResponse> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                moviesAPI.searchMovieByQuery(
                    API_KEY,
                    query,
                    LANGUAGE_ES, REGION_COLOMBIA
                ).await()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}