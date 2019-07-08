package gug.co.com.testmovies.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import gug.co.com.testmovies.MainCoroutineRule
import gug.co.com.testmovies.data.source.local.room.MoviesDatabase
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.movies.MoviesFilter
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
class MoviesDaoTest {

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
    fun insertMovieAndGetById() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, null)
        database.moviesDao().insertAll(movie)

        // WHEN - Get the DbMovie by id from the database
        val loaded = database.moviesDao().getMovieById(movieId = movie.id)

        // THEN - The loaded data contains the expected values
        assertThat<DbMovie>(loaded as DbMovie, CoreMatchers.notNullValue())
        assertThat(loaded.id, CoreMatchers.`is`(movie.id))
        assertThat(loaded.title, CoreMatchers.`is`(movie.title))
        assertThat(loaded.posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(loaded.voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }

    @Test
    fun insertPopularMovieAndGetPopularMovies() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()
        val popular = MoviesFilter.POPULAR

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, popular)
        database.moviesDao().insertAll(movie)

        // WHEN - Get Populars movies from the database
        val movies = database.moviesDao().getPopularMovies()

        // THEN - The loaded data contains the expected values
        assertThat(movies.size, CoreMatchers.`is`(1))
        assertThat(movies[0].id, CoreMatchers.`is`(movie.id))
        assertThat(movies[0].title, CoreMatchers.`is`(movie.title))
        assertThat(movies[0].posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(movies[0].voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }

    @Test
    fun insertTopRatedMovieAndGetTopRatedMovies() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()
        val popular = MoviesFilter.TOP_RATED

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, popular)
        database.moviesDao().insertAll(movie)

        // WHEN - Get TopRated movies from the database
        val movies = database.moviesDao().getTopRatedMovies()

        // THEN - The loaded data contains the expected values
        assertThat(movies.size, CoreMatchers.`is`(1))
        assertThat(movies[0].id, CoreMatchers.`is`(movie.id))
        assertThat(movies[0].title, CoreMatchers.`is`(movie.title))
        assertThat(movies[0].posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(movies[0].voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }

    @Test
    fun insertUpComingMovieAndGetUpComingMovies() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()
        val popular = MoviesFilter.UP_COMING

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, popular)
        database.moviesDao().insertAll(movie)

        // WHEN - Get Populars movies from the database
        val movies = database.moviesDao().getUpComingMovies()

        // THEN - The loaded data contains the expected values
        assertThat(movies.size, CoreMatchers.`is`(1))
        assertThat(movies[0].id, CoreMatchers.`is`(movie.id))
        assertThat(movies[0].title, CoreMatchers.`is`(movie.title))
        assertThat(movies[0].posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(movies[0].voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }


    @Test
    fun insertPopularMovieAndSearchPopularMovies() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()
        val popular = MoviesFilter.POPULAR

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, popular)
        database.moviesDao().insertAll(movie)

        // WHEN - Get Populars movies from the database
        val movies = database.moviesDao().searchPopularMovies("ori")

        // THEN - The loaded data contains the expected values
        assertThat(movies.size, CoreMatchers.`is`(1))
        assertThat(movies[0].id, CoreMatchers.`is`(movie.id))
        assertThat(movies[0].title, CoreMatchers.`is`(movie.title))
        assertThat(movies[0].posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(movies[0].voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }

    @Test
    fun insertTopRatedMovieAndSearchTopRatedMovies() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()
        val popular = MoviesFilter.TOP_RATED

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, popular)
        database.moviesDao().insertAll(movie)

        // WHEN - Get Populars movies from the database
        val movies = database.moviesDao().searchTopRatedMovies("ori")

        // THEN - The loaded data contains the expected values
        assertThat(movies.size, CoreMatchers.`is`(1))
        assertThat(movies[0].id, CoreMatchers.`is`(movie.id))
        assertThat(movies[0].title, CoreMatchers.`is`(movie.title))
        assertThat(movies[0].posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(movies[0].voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }

    @Test
    fun insertUpComingMovieAndSearchComingMovies() = runBlockingTest {
        val dbutils = DbEntitiesTestUtils()
        val popular = MoviesFilter.UP_COMING

        // GIVEN - insert a DbMovie
        val movie = dbutils.getDbMovieForTest(1, popular)
        database.moviesDao().insertAll(movie)

        // WHEN - Get Populars movies from the database
        val movies = database.moviesDao().searchUpComingMovies("ori")

        // THEN - The loaded data contains the expected values
        assertThat(movies.size, CoreMatchers.`is`(1))
        assertThat(movies[0].id, CoreMatchers.`is`(movie.id))
        assertThat(movies[0].title, CoreMatchers.`is`(movie.title))
        assertThat(movies[0].posterPath, CoreMatchers.`is`(movie.posterPath))
        assertThat(movies[0].voteAverage, CoreMatchers.`is`(movie.voteAverage))
    }

}

