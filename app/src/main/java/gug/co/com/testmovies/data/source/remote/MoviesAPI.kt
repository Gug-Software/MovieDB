package gug.co.com.testmovies.data.source.remote

import gug.co.com.testmovies.data.source.remote.dtos.DtoMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesAPI {

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("region") region: String
    ): Deferred<List<DtoMovieResponse>>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("region") region: String
    ): Deferred<List<DtoMovieResponse>>

    @GET("movie/upcoming")
    fun getUpComing(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("region") region: String
    ): Deferred<List<DtoMovieResponse>>

}