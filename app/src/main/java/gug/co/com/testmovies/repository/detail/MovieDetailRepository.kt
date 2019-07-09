package gug.co.com.testmovies.repository.detail

import gug.co.com.testmovies.data.source.local.MovieDetailLocalDataStore
import gug.co.com.testmovies.data.source.local.room.entities.*
import gug.co.com.testmovies.data.source.remote.MovieDetailRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.details.asDatabaseModel
import gug.co.com.testmovies.data.source.remote.retrofit.dtos.movies.videos.asDatabaseModel
import gug.co.com.testmovies.ui.detail.IContractMovieDetail
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailRepository(
    private val movieDetailRemoteDataStore: MovieDetailRemoteDataStore,
    private val movieDetailLocalDataStore: MovieDetailLocalDataStore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractMovieDetail.Model {

    /**
     * Always update the movie in databaselocal with the details from remote source
     * We need save in databaselocal the listdetail information
     */
    override suspend fun getMovieDetailById(movieId: Int, moviesFilter: MoviesFilter): Result<DbMovie> {

        return withContext(ioDispatcher) {

            val movieRemote = movieDetailRemoteDataStore.getMovieDetail(movieId)
            (movieRemote as? Result.Success)?.let { movieDetail ->

                movieDetailLocalDataStore.update(movieDetail.data.asDatabaseModel(moviesFilter))
                movieDetailLocalDataStore.insertGenres(*movieDetail.data.dtoGenres.asDatabaseModel(movieId))
                movieDetailLocalDataStore.insertProductionCompanies(
                    *movieDetail.data.dtoProductionCompanies.asDatabaseModel(
                        movieId
                    )
                )
                movieDetailLocalDataStore.insertSpokenLaguages(
                    *movieDetail.data.dtoSpokenLanguages.asDatabaseModel(
                        movieId
                    )
                )

                val videosRemote = movieDetailRemoteDataStore.getMovieVideos(movieId)
                (videosRemote  as? Result.Success)?.let { videos ->
                    movieDetailLocalDataStore.insertVideos(*videosRemote.data.results.asDatabaseModel(movieId))
                }

                return@withContext Result.Success(movieDetail.data.asDatabaseModel(moviesFilter))

            }

            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>> = withContext(ioDispatcher) {
        movieDetailLocalDataStore.getGenresByMovieId(movieId)
    }

    override suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>> =
        withContext(ioDispatcher) {
            movieDetailLocalDataStore.getProductionCompaniesByMovieId(movieId)
        }

    override suspend fun getSpokenLanguagesByMovieId(movieId: Int): Result<List<DbSpokenLanguage>> =
        withContext(ioDispatcher) {
            movieDetailLocalDataStore.getSpokenLaguageByMovieId(movieId)
        }

    override suspend fun getVideosByMovieId(movieId: Int): Result<List<DbVideo>> =
        withContext(ioDispatcher) {
            movieDetailLocalDataStore.getVideosByMovieId(movieId)
        }

}