package gug.co.com.testmovies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gug.co.com.testmovies.data.source.local.entities.DbMovie
import gug.co.com.testmovies.data.source.local.entities.DbVideo

@Dao
interface VideosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dbVideo: DbVideo)

    @Query("SELECT * FROM DbVideo WHERE movieId= :movieId")
    suspend fun getVideosByMovie(movieId: Int): List<DbVideo>

}