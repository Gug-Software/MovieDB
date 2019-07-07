package gug.co.com.testmovies.data.source.local.room.dao

import androidx.room.*
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM DbMovie WHERE isPopular= 1")
    suspend fun getPopularMovies(): List<DbMovie>

    @Query("SELECT * FROM DbMovie WHERE isTopRated= 1")
    suspend fun getTopRatedMovies(): List<DbMovie>

    @Query("SELECT * FROM DbMovie WHERE isUpComing= 1")
    suspend fun getUpComingMovies(): List<DbMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg movies: DbMovie)

    @Update
    suspend fun update(movie: DbMovie)

}