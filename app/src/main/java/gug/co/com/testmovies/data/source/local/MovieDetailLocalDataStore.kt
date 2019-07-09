package gug.co.com.testmovies.data.source.local

import gug.co.com.testmovies.data.source.local.room.entities.*
import gug.co.com.testmovies.utils.Result

interface MovieDetailLocalDataStore {

    /**
     * Update the movie in databaselocal with detail information
     */
    suspend fun update(movie: DbMovie)

    /**
     * Insert the movie´s genres
     */
    suspend fun insertGenres(vararg genres: DbGenre)

    /**
     * Insert the movie´s production companies
     */
    suspend fun insertProductionCompanies(vararg productionCompanies: DbProductionCompany)

    /**
     * Insert the movie´s Spoken Languages
     */
    suspend fun insertSpokenLaguages(vararg spokenLanguages: DbSpokenLanguage)

    /**
     * Insert all the videos for movie
     */
    suspend fun insertVideos(vararg dbVideos: DbVideo)

    /**
     * Get all the movie information for movieid
     */
    suspend fun getMovieById(movieId: Int): Result<DbMovie>

    /**
     * get all the genres of movie
     */
    suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>>

    /**
     * get all the laguages for movie
     */
    suspend fun getSpokenLaguageByMovieId(movieId: Int): Result<List<DbSpokenLanguage>>

    /**
     * get all de production companies by movie
     */
    suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>>

    /**
     * Get all the videos for movie
     */
    suspend fun getVideosByMovieId(movieId: Int): Result<List<DbVideo>>


}