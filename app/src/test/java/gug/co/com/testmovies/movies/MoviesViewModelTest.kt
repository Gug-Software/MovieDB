package gug.co.com.testmovies.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import gug.co.com.testmovies.*
import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.local.room.entities.asDomainModel
import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.NetworkApiStatus
import gug.co.com.testmovies.repository.movies.DbEntitiesTestUtils
import gug.co.com.testmovies.repository.movies.MoviesRepository
import gug.co.com.testmovies.repository.movies.RemoteDtosTestUtils
import gug.co.com.testmovies.utils.movies.MoviesFilter
import gug.co.com.testmovies.viewmodels.movies.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the implementation of [MoviesViewModel]
 */
@ExperimentalCoroutinesApi
class MoviesViewModelTest {

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

    private lateinit var viewModel: MoviesViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainTestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        // We initialise the tasks to 3, with one active and two completed
        moviesRemoteDataSource = FakeRemoteDataSource(remotePopularMovies, remoteTopRatedMovies, remoteUpComingMovies)
        moviesLocalDataSource = FakeLocalDataSource(dbMovies.toMutableList())
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            moviesRemoteDataSource, moviesLocalDataSource, Dispatchers.Unconfined
        )

        viewModel = MoviesViewModel(moviesRepository)

    }

    @Test
    fun loadPopularMoviesFromRepository_loadingTogglesAndDataLoaded() {

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Given an initialized TasksViewModel with initialized tasks
        // When loading of Tasks is requested
        // Trigger loading of tasks
        viewModel.loadMovies(MoviesFilter.POPULAR)

        // Then progress indicator is shown
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.status))
            .isEqualTo(NetworkApiStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.status))
            .isEqualTo(NetworkApiStatus.DONE)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.movies)).hasSize(2)
    }

    @Test
    fun loadTopRatedMoviesFromRepository_loadingTogglesAndDataLoaded() {

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Given an initialized TasksViewModel with initialized tasks
        // When loading of Tasks is requested
        // Trigger loading of tasks
        viewModel.loadMovies(MoviesFilter.TOP_RATED)

        // Then progress indicator is shown
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.status))
            .isEqualTo(NetworkApiStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.status))
            .isEqualTo(NetworkApiStatus.DONE)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.movies)).hasSize(2)
    }

    @Test
    fun loadUpComingMoviesFromRepository_loadingTogglesAndDataLoaded() {

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Given an initialized TasksViewModel with initialized tasks
        // When loading of Tasks is requested
        // Trigger loading of tasks
        viewModel.loadMovies(MoviesFilter.UP_COMING)

        // Then progress indicator is shown
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.status))
            .isEqualTo(NetworkApiStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.status))
            .isEqualTo(NetworkApiStatus.DONE)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(viewModel.movies)).hasSize(1)
    }

    @Test
    fun clickOnMovie_setmovieNavigate() {

        // When opening a new Movie
        val movie = dbMovies.asDomainModel()[0]
        viewModel.showMovieDetail(movie)

        // Then the event is triggered
        assertLiveDataMovieTriggered(viewModel.navToDetailMovie, movie.id)
    }
}