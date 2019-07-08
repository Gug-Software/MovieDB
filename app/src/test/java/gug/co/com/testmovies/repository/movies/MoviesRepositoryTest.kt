package gug.co.com.testmovies.repository.movies

import com.google.common.truth.Truth
import gug.co.com.testmovies.FakeLocalDataSource
import gug.co.com.testmovies.FakeRemoteDataSource
import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.asDatabaseModel
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class MoviesRepositoryTest {

    private val remoteDtos = RemoteDtosTestUtils()
    private val localDb = DbEntitiesTestUtils()
    /**
     * POPULAR [1, 2] TOP_RATED [3, 4] UP_COMING [5]
     */
    private val dtoMovie1 = remoteDtos.getDtoMovieForTest(1)
    private val dtoMovie2 = remoteDtos.getDtoMovieForTest(2)
    private val dtoMovie3 = remoteDtos.getDtoMovieForTest(3)
    private val dtoMovie4 = remoteDtos.getDtoMovieForTest(4)
    private val dtoMovie5 = remoteDtos.getDtoMovieForTest(5)
    private val remotePopularMovies = listOf(
        dtoMovie1, dtoMovie2
    )
    private val remoteTopRatedMovies = listOf(
        dtoMovie3, dtoMovie4
    )
    private val remoteUpComingMovies = listOf(
        dtoMovie5
    )
    private val dbMovie1 = localDb.getDbMovieForTest(1, MoviesFilter.POPULAR)
    private val dbMovie2 = localDb.getDbMovieForTest(2, MoviesFilter.POPULAR)
    private val dbMovie3 = localDb.getDbMovieForTest(3, MoviesFilter.TOP_RATED)
    private val dbMovie4 = localDb.getDbMovieForTest(4, MoviesFilter.TOP_RATED)
    private val dbMovie5 = localDb.getDbMovieForTest(5, MoviesFilter.UP_COMING)
    private val dbMovies = listOf(
        dbMovie1, dbMovie2, dbMovie3, dbMovie4, dbMovie5
    )
    private lateinit var moviesRemoteDataSource: MoviesRemoteDataStore
    private lateinit var moviesLocalDataSource: MoviesLocalDataStore

    // Class under test
    private lateinit var moviesRepository: MoviesRepository

    @ExperimentalCoroutinesApi
    @Before
    fun createRepository() {

        moviesRemoteDataSource = FakeRemoteDataSource(remotePopularMovies, remoteTopRatedMovies, remoteUpComingMovies)
        moviesLocalDataSource = FakeLocalDataSource(dbMovies.toMutableList())
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            moviesRemoteDataSource, moviesLocalDataSource, Dispatchers.Unconfined
        )

    }

    @Test
    fun getMovies_popular_LocalEmptyAndRemote() = runBlockingTest {

        val emptyLocal = FakeLocalDataSource()
        moviesRemoteDataSource = FakeRemoteDataSource(remotePopularMovies, remoteTopRatedMovies, remoteUpComingMovies)
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            moviesRemoteDataSource, emptyLocal, Dispatchers.Unconfined
        )

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.POPULAR) as Result.Success
        val moviesRemote = remotePopularMovies.asDatabaseModel(MoviesFilter.POPULAR).asList()

        // Then tasks are loaded from the remote data source
        Truth.assertThat(movies.data.size).isEqualTo(moviesRemote.size)
    }

    @Test
    fun getMovies_topRated_LocalEmptyAndRemote() = runBlockingTest {

        val emptyLocal = FakeLocalDataSource()
        moviesRemoteDataSource = FakeRemoteDataSource(remotePopularMovies, remoteTopRatedMovies, remoteUpComingMovies)
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            moviesRemoteDataSource, emptyLocal, Dispatchers.Unconfined
        )

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.TOP_RATED) as Result.Success
        val moviesRemote = remoteTopRatedMovies.asDatabaseModel(MoviesFilter.TOP_RATED).asList()

        // Then tasks are loaded from the remote data source
        Truth.assertThat(movies.data.size).isEqualTo(moviesRemote.size)
    }

    @Test
    fun getMovies_upcoming_LocalEmptyAndRemote() = runBlockingTest {

        val emptyLocal = FakeLocalDataSource()
        moviesRemoteDataSource = FakeRemoteDataSource(remotePopularMovies, remoteTopRatedMovies, remoteUpComingMovies)
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            moviesRemoteDataSource, emptyLocal, Dispatchers.Unconfined
        )

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.UP_COMING) as Result.Success
        val moviesRemote = remoteUpComingMovies.asDatabaseModel(MoviesFilter.UP_COMING).asList()

        // Then tasks are loaded from the remote data source
        Truth.assertThat(movies.data.size).isEqualTo(moviesRemote.size)
    }

    @Test
    fun getMovies_popular_LocalAndRemote() = runBlockingTest {

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.POPULAR) as Result.Success
        val moviesRemote = remotePopularMovies.asDatabaseModel(MoviesFilter.POPULAR).asList()

        // Then tasks are loaded from the remote data source
        Truth.assertThat(movies.data.size).isEqualTo(moviesRemote.size)
    }

    @Test
    fun getMovies_topRated_LocalAndRemote() = runBlockingTest {

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.TOP_RATED) as Result.Success
        val moviesRemote = remoteTopRatedMovies.asDatabaseModel(MoviesFilter.TOP_RATED).asList()

        // Then tasks are loaded from the remote data source
        Truth.assertThat(movies.data.size).isEqualTo(moviesRemote.size)
    }

    @Test
    fun getMovies_upcoming_LocalAndRemote() = runBlockingTest {

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.UP_COMING) as Result.Success
        val moviesRemote = remoteUpComingMovies.asDatabaseModel(MoviesFilter.UP_COMING).asList()

        // Then tasks are loaded from the remote data source
        Truth.assertThat(movies.data.size).isEqualTo(moviesRemote.size)
    }

    @Test
    fun searchPopularMoviesByFilter() = runBlockingTest {

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.POPULAR) as Result.Success
        val movieSearch =
            moviesRepository.searchMoviesByQueryAndFilter("Title-1", MoviesFilter.POPULAR, false) as Result.Success

        MatcherAssert.assertThat(movieSearch.data.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(movieSearch.data[0].id, CoreMatchers.`is`(dbMovie1.id))
        MatcherAssert.assertThat(movieSearch.data[0].title, CoreMatchers.`is`(dbMovie1.title))
        MatcherAssert.assertThat(movieSearch.data[0].posterPath, CoreMatchers.`is`(dbMovie1.posterPath))
        MatcherAssert.assertThat(movieSearch.data[0].voteAverage, CoreMatchers.`is`(dbMovie1.voteAverage))
    }

    @Test
    fun searchTopRatedMoviesByFilter() = runBlockingTest {

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.TOP_RATED) as Result.Success
        val movieSearch =
            moviesRepository.searchMoviesByQueryAndFilter("Title-3", MoviesFilter.TOP_RATED, false) as Result.Success

        MatcherAssert.assertThat(movieSearch.data.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(movieSearch.data[0].id, CoreMatchers.`is`(dbMovie3.id))
        MatcherAssert.assertThat(movieSearch.data[0].title, CoreMatchers.`is`(dbMovie3.title))
        MatcherAssert.assertThat(movieSearch.data[0].posterPath, CoreMatchers.`is`(dbMovie3.posterPath))
        MatcherAssert.assertThat(movieSearch.data[0].voteAverage, CoreMatchers.`is`(dbMovie3.voteAverage))
    }


    @Test
    fun searchUpComingMoviesByFilter() = runBlockingTest {

        val movies = moviesRepository.getMoviesByFilter(MoviesFilter.UP_COMING) as Result.Success
        val movieSearch =
            moviesRepository.searchMoviesByQueryAndFilter("Title-5", MoviesFilter.UP_COMING, false) as Result.Success

        MatcherAssert.assertThat(movieSearch.data.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(movieSearch.data[0].id, CoreMatchers.`is`(dbMovie5.id))
        MatcherAssert.assertThat(movieSearch.data[0].title, CoreMatchers.`is`(dbMovie5.title))
        MatcherAssert.assertThat(movieSearch.data[0].posterPath, CoreMatchers.`is`(dbMovie5.posterPath))
        MatcherAssert.assertThat(movieSearch.data[0].voteAverage, CoreMatchers.`is`(dbMovie5.voteAverage))
    }


}