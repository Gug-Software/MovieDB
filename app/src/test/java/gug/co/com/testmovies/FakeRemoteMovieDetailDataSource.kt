package gug.co.com.testmovies

import gug.co.com.testmovies.data.source.remote.MovieDetailRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details.DtoMovieDetail
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.Result.Success

class FakeRemoteMovieDetailDataSource(
    var movies: List<DtoMovieDetail>? = listOf()
) :
    MovieDetailRemoteDataStore {

    override suspend fun getMovieDetail(movieId: Int): Result<DtoMovieDetail> {
        return Success(movies!!.get(0))
    }

}