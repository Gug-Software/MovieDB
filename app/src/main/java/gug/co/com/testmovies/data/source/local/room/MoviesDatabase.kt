package gug.co.com.testmovies.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gug.co.com.testmovies.data.source.local.room.dao.*
import gug.co.com.testmovies.data.source.local.room.entities.*

const val DB_NAME = "MoviesDb"

@Database(

    entities = [
        DbMovie::class,
        DbGenre::class,
        DbProductionCompany::class,
        DbSpokenLanguage::class,
        DbVideo::class
    ],

    version = 1

)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract val genreDao: GenreDao
    abstract val productionCompanyDao: ProductionCompanyDao
    abstract val spokenLanguageDao: SpokenLanguageDao
    abstract val videosDao: VideosDao

    companion object {

        private lateinit var INSTANCE: MoviesDatabase

        fun getDatabase(context: Context): MoviesDatabase {

            synchronized(MoviesDatabase::class.java) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }

            return INSTANCE

        }
    }
}