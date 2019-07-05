package gug.co.com.testmovies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gug.co.com.testmovies.data.source.local.entities.DbSpokenLanguage

@Dao
interface SpokenLanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dbSpokenLanguage: DbSpokenLanguage)

    @Query("SELECT * FROM DbSpokenLanguage WHERE movieId= :movieId")
    suspend fun getSpokenLanguagesByMovie(movieId: Int): List<DbSpokenLanguage>

}