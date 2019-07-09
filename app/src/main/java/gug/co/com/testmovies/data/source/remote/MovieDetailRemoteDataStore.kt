package gug.co.com.testmovies.data.source.remote

import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details.DtoMovieDetail
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.videos.DtoMovieVideosResponse
import gug.co.com.testmovies.utils.Result

interface MovieDetailRemoteDataStore {

    suspend fun getMovieDetail(movieId: Int): Result<DtoMovieDetail>

    suspend fun getMovieVideos(movieId: Int): Result<DtoMovieVideosResponse>

}