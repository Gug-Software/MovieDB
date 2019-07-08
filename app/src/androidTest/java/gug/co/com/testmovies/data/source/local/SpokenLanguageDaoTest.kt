package gug.co.com.testmovies.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import gug.co.com.testmovies.MainCoroutineRule
import gug.co.com.testmovies.data.source.local.room.MoviesDatabase
import gug.co.com.testmovies.data.source.local.room.entities.DbSpokenLanguage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SpokenLanguageDaoTest {

    private lateinit var database: MoviesDatabase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            MoviesDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertSpokenLanguageAndGetByMovieId() = runBlockingTest {

        val dbutils = DbEntitiesTestUtils()
        val movieId = 1
        // GIVEN - insert  language to movie
        val language1 = dbutils.getDbSpokenLanguage(movieId, 1)
        database.spokenLanguageDao().insertAll(language1)

        // WHEN - Get the languages by Movie
        val loaded = database.spokenLanguageDao().getSpokenLanguagesByMovie(1)

        // THEN - The loaded data contains the expected values
        assertThat<List<DbSpokenLanguage>>(loaded as List<DbSpokenLanguage>, CoreMatchers.notNullValue())
        assertThat(loaded[0].movieId, CoreMatchers.`is`(movieId))
        assertThat(loaded[0].iso6391, CoreMatchers.`is`(language1.iso6391))
        assertThat(loaded[0].name, CoreMatchers.`is`(language1.name))
    }

    @Test
    fun insertTwoSpokenLanguagesAndGetByMovieId() = runBlockingTest {

        val dbutils = DbEntitiesTestUtils()
        val movieId = 1
        // GIVEN - insert Two languages to movie
        val language1 = dbutils.getDbSpokenLanguage(movieId, 1)
        val language2 = dbutils.getDbSpokenLanguage(movieId, 2)
        database.spokenLanguageDao().insertAll(language1, language2)

        // WHEN - Get the languages by Movie
        val loaded = database.spokenLanguageDao().getSpokenLanguagesByMovie(1)

        // THEN - The loaded data contains the expected values
        assertThat<List<DbSpokenLanguage>>(loaded as List<DbSpokenLanguage>, CoreMatchers.notNullValue())
        assertThat(loaded.size, CoreMatchers.`is`(2))

    }

}

