package gug.co.com.testmovies.repository.moviedetail

import com.google.common.truth.Truth
import gug.co.com.testmovies.FakeLocalMovieDetailDataSource
import gug.co.com.testmovies.FakeRemoteMovieDetailDataSource
import gug.co.com.testmovies.data.source.local.MovieDetailLocalDataStore
import gug.co.com.testmovies.data.source.remote.MovieDetailRemoteDataStore
import gug.co.com.testmovies.repository.detail.MovieDetailRepository
import gug.co.com.testmovies.repository.movies.DbEntitiesTestUtils
import gug.co.com.testmovies.repository.movies.RemoteDtosTestUtils
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class MovieDetailRepositoryTest {

    private val remoteDtos = RemoteDtosTestUtils()
    private val localDb = DbEntitiesTestUtils()

    private val dtoMovie1 = remoteDtos.getDtoMovieDetailForTest(1)
    private val dbMovie1 = localDb.getDbMovieForTest(1, MoviesFilter.POPULAR)

    private lateinit var movieDetailRemoteDataStore: MovieDetailRemoteDataStore
    private lateinit var movieDetailLocalDataStore: MovieDetailLocalDataStore

    // Class under test
    private lateinit var movieDetailRepository: MovieDetailRepository

    @ExperimentalCoroutinesApi
    @Before
    fun createRepository() {

        movieDetailRemoteDataStore = FakeRemoteMovieDetailDataSource(listOf(dtoMovie1))
        movieDetailLocalDataStore = FakeLocalMovieDetailDataSource(listOf(dbMovie1).toMutableList())
        // Get a reference to the class under test
        movieDetailRepository = MovieDetailRepository(
            movieDetailRemoteDataStore, movieDetailLocalDataStore, Dispatchers.Unconfined
        )

    }

    @Test
    fun getMovieDetail_Genres() = runBlockingTest {

        val movieId = 1
        val movie = movieDetailRepository.getMovieDetailById(movieId, MoviesFilter.POPULAR) as Result.Success
        val genres = movieDetailRepository.getGenresByMovieId(movieId) as Result.Success

        //
        Truth.assertThat(genres.data.size).isEqualTo(2)
    }

    @Test
    fun getMovieDetail_ProductionCompanies() = runBlockingTest {

        val movieId = 1
        val movie = movieDetailRepository.getMovieDetailById(movieId, MoviesFilter.POPULAR) as Result.Success
        val companies = movieDetailRepository.getProductionCompaniesByMovieId(movieId) as Result.Success

        //
        Truth.assertThat(companies.data.size).isEqualTo(2)
    }

    @Test
    fun getMovieDetail_SpokenLanguages() = runBlockingTest {

        val movieId = 1
        val movie = movieDetailRepository.getMovieDetailById(movieId, MoviesFilter.POPULAR) as Result.Success
        val languages = movieDetailRepository.getSpokenLanguagesByMovieId(movieId) as Result.Success

        //
        Truth.assertThat(languages.data.size).isEqualTo(2)
    }


    @Test
    fun getMovieDetail_fromRemote() = runBlockingTest {

        val movieId = 1
        val movieBefore = dbMovie1
        val movie = movieDetailRepository.getMovieDetailById(movieId, MoviesFilter.POPULAR) as Result.Success

        //
        Truth.assertThat(movie.data.homepage).isNotEqualTo(movieBefore.homepage)
        Truth.assertThat(movie.data.voteAverage).isEqualTo(movieBefore.voteAverage)
    }

}