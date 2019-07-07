package gug.co.com.testmovies.data.source.remote

import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovieResponse
import gug.co.com.testmovies.utils.Result

interface MoviesRemoteDataStore {

    suspend fun getPopularMovies(): Result<DtoMovieResponse>

    suspend fun getTopRatedMovies(): Result<DtoMovieResponse>

    suspend fun getUpComingMovies(): Result<DtoMovieResponse>

}