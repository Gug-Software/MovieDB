package gug.co.com.testmovies.data.source.remote.retrofit

import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details.DtoMovieDetail
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.discover.DtoMovieResponse
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.videos.DtoMovieVideosResponse
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.search.movies.DtoSearchMoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    /**
     * Get a list of the current popular movies on TMDb. This list updates daily.
     * https://developers.themoviedb.org/3/movies/get-popular-movies
     *
     * @param apiKey Clave de la API (v3 auth)
     * @param language Specify a language to query translatable fields with.
     * @param region Specify a ISO 3166-1 code to filter release dates. Must be uppercase.
     */
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String, @Query("language") language: String,
        @Query("region") region: String
    ): Deferred<DtoMovieResponse>

    /**
     * Get the top rated movies on TMDb.
     * https://developers.themoviedb.org/3/movies/get-top-rated-movies
     *
     * @param apiKey Clave de la API (v3 auth)
     * @param language Specify a language to query translatable fields with.
     * @param region Specify a ISO 3166-1 code to filter release dates. Must be uppercase.
     */
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String, @Query("language") language: String,
        @Query("region") region: String
    ): Deferred<DtoMovieResponse>

    /**
     * Get a list of upcoming movies in theatres.
     * https://developers.themoviedb.org/3/movies/get-upcoming
     *
     * @param apiKey Clave de la API (v3 auth)
     * @param language Specify a language to query translatable fields with.
     * @param region Specify a ISO 3166-1 code to filter release dates. Must be uppercase.
     */
    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("api_key") apiKey: String, @Query("language") language: String,
        @Query("region") region: String
    ): Deferred<DtoMovieResponse>

    /**
     * Get the primary information about a movie.
     * https://developers.themoviedb.org/3/movies/get-movie-details
     *
     * @param id movieId
     * @param apiKey Clave de la API (v3 auth)
     * @param language Specify a language to query translatable fields with.
     */
    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int, @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Deferred<DtoMovieDetail>

    /**
     * Get the videos that have been added to a movie.
     * https://developers.themoviedb.org/3/movies/get-movie-videos
     *
     * @param id movieId
     * @param apiKey Clave de la API (v3 auth)
     * @param language Specify a language to query translatable fields with.
     */
    @GET("movie/{id}/videos")
    fun getMovieVideos(
        @Path("id") id: Int, @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Deferred<DtoMovieVideosResponse>

    /**
     * Search for movies.
     * https://developers.themoviedb.org/3/search/search-movies
     *
     *  @param apiKey Clave de la API (v3 auth)
     *  @param query Pass a text query to search. This value should be URI encoded.
     *  @param language Specify a language to query translatable fields with.
     *  @param region Specify a ISO 3166-1 code to filter release dates. Must be uppercase.
     */
    @GET("search/movie")
    fun searchMovieByQuery(
        @Query("api_key") apiKey: String, @Query("query") query: String,
        @Query("language") language: String, @Query("region") region: String
    ): Deferred<DtoSearchMoviesResponse>


}