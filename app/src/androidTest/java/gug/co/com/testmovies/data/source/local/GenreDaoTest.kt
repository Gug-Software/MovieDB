package gug.co.com.testmovies.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import gug.co.com.testmovies.MainCoroutineRule
import gug.co.com.testmovies.data.source.local.room.MoviesDatabase
import gug.co.com.testmovies.data.source.local.room.entities.DbGenre
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
class GenreDaoTest {

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
    fun insertGenreAndGetByMovieId() = runBlockingTest {

        val dbutils = DbEntitiesTestUtils()
        val movieId = 1
        // GIVEN - insert Two genres to movie
        val genre1 = dbutils.getDbGenre(movieId, 1)
        database.genreDao().insertAll(genre1)

        // WHEN - Get the genres by Movie
        val loaded = database.genreDao().getGenresByMovie(1)

        // THEN - The loaded data contains the expected values
        assertThat<List<DbGenre>>(loaded as List<DbGenre>, CoreMatchers.notNullValue())
        assertThat(loaded[0].movieId, CoreMatchers.`is`(movieId))
        assertThat(loaded[0].id, CoreMatchers.`is`(genre1.id))
        assertThat(loaded[0].name, CoreMatchers.`is`(genre1.name))
    }

    @Test
    fun insertTwoGenresAndGetByMovieId() = runBlockingTest {

        val dbutils = DbEntitiesTestUtils()
        val movieId = 1
        // GIVEN - insert Two genres to movie
        val genre1 = dbutils.getDbGenre(movieId, 1)
        val genre2 = dbutils.getDbGenre(movieId, 2)
        database.genreDao().insertAll(genre1, genre2)

        // WHEN - Get the genres by Movie
        val loaded = database.genreDao().getGenresByMovie(1)

        // THEN - The loaded data contains the expected values
        assertThat<List<DbGenre>>(loaded as List<DbGenre>, CoreMatchers.notNullValue())
        assertThat(loaded.size, CoreMatchers.`is`(2))
    }

}

