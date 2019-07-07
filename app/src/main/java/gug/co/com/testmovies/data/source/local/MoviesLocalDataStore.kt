package gug.co.com.testmovies.data.source.local

import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.Result

interface MoviesLocalDataStore {

    suspend fun getPopularMovies(): Result<List<DbMovie>>

    suspend fun getTopRatedMovies(): Result<List<DbMovie>>

    suspend fun getUpComingMovies(): Result<List<DbMovie>>

    suspend fun insertAll(vararg movies: DbMovie)

    suspend fun update(movie: DbMovie)

}