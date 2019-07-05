package gug.co.com.testmovies.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gug.co.com.testmovies.data.source.local.dao.*
import gug.co.com.testmovies.data.source.local.entities.*

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
abstract class PostsDataBase : RoomDatabase() {

    abstract val moviesDao: MoviesDao
    abstract val genreDao: GenreDao
    abstract val productionCompanyDao: ProductionCompanyDao
    abstract val spokenLanguageDao: SpokenLanguageDao
    abstract val videosDao: VideosDao

    companion object {

        private lateinit var INSTANCE: PostsDataBase

        fun getDatabase(context: Context): PostsDataBase {

            synchronized(PostsDataBase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PostsDataBase::class.java,
                        DB_NAME
                    ).build()
                }
            }

            return INSTANCE

        }
    }
}