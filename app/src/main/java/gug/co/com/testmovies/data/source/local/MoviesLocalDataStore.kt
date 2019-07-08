package gug.co.com.testmovies.data.source.local

import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovieResponse
import gug.co.com.testmovies.utils.Result

interface MoviesLocalDataStore {

    suspend fun getPopularMovies(): Result<List<DbMovie>>

    suspend fun getTopRatedMovies(): Result<List<DbMovie>>

    suspend fun getUpComingMovies(): Result<List<DbMovie>>

    suspend fun searchPopularMovies(query: String): Result<List<DbMovie>>

    suspend fun searchTopRatedMovies(query: String): Result<List<DbMovie>>

    suspend fun searchUpComingMovies(query: String): Result<List<DbMovie>>

    suspend fun searchMovies(query: String): Result<List<DbMovie>>

    suspend fun insertAll(vararg movies: DbMovie)

    suspend fun update(movie: DbMovie)

}