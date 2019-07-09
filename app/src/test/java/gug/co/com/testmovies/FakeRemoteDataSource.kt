package gug.co.com.testmovies

import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovie
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovieResponse
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.search.movies.DtoSearchMoviesResponse
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.Result.Success

class FakeRemoteDataSource(
    var popularMovies: List<DtoMovie>? = listOf(),
    var topRatedMovies: List<DtoMovie>? = listOf(),
    var upComingMovies: List<DtoMovie>? = listOf()
) :
    MoviesRemoteDataStore {

    override suspend fun searchMovies(query: String): Result<DtoSearchMoviesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPopularMovies(): Result<DtoMovieResponse> {

        var dtoMovieResponse = popularMovies?.let {
            DtoMovieResponse(
                1,
                it,
                100,
                1000
            )
        }

        return Success(dtoMovieResponse!!)
    }

    override suspend fun getTopRatedMovies(): Result<DtoMovieResponse> {

        var dtoMovieResponse = topRatedMovies?.let {
            DtoMovieResponse(
                1,
                it,
                100,
                1000
            )
        }

        return Success(dtoMovieResponse!!)
    }

    override suspend fun getUpComingMovies(): Result<DtoMovieResponse> {

        var dtoMovieResponse = upComingMovies?.let {
            DtoMovieResponse(
                1,
                it,
                100,
                1000
            )
        }

        return Success(dtoMovieResponse!!)
    }

}