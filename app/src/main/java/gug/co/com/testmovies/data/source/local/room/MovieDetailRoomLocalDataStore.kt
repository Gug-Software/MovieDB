package gug.co.com.testmovies.data.source.local.room

import gug.co.com.testmovies.data.source.local.MovieDetailLocalDataStore
import gug.co.com.testmovies.data.source.local.room.dao.*
import gug.co.com.testmovies.data.source.local.room.entities.*
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.Result.Error
import gug.co.com.testmovies.utils.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailRoomLocalDataStore(
    private val moviesDao: MoviesDao,
    private val genreDao: GenreDao,
    private val productionCompanyDao: ProductionCompanyDao,
    private val spokenLanguageDao: SpokenLanguageDao,
    private val videosDao: VideosDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDetailLocalDataStore {

    override suspend fun insertGenres(vararg genres: DbGenre) = withContext(ioDispatcher) {
        genreDao.insertAll(*genres)
    }

    override suspend fun insertProductionCompanies(vararg productionCompanies: DbProductionCompany) =
        withContext(ioDispatcher) {
            productionCompanyDao.insertAll(*productionCompanies)
        }

    override suspend fun insertSpokenLaguages(vararg spokenLanguages: DbSpokenLanguage) = withContext(ioDispatcher) {
        spokenLanguageDao.insertAll(*spokenLanguages)
    }

    override suspend fun insertVideos(vararg dbVideos: DbVideo) = withContext(ioDispatcher) {
        videosDao.insertAll(*dbVideos)
    }

    override suspend fun getMovieById(movieId: Int): Result<DbMovie> = withContext(ioDispatcher) {
        try {
            val movie = moviesDao.getMovieById(movieId)
            if (movie != null) {
                return@withContext Success(movie)
            } else {
                return@withContext Error(Exception("Movie not found!"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(genreDao.getGenresByMovie(movieId))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getSpokenLaguageByMovieId(movieId: Int): Result<List<DbSpokenLanguage>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(spokenLanguageDao.getSpokenLanguagesByMovie(movieId))
            } catch (e: Exception) {
                Error(e)
            }
        }

    override suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(productionCompanyDao.getProductionCompaniesByMovie(movieId))
            } catch (e: Exception) {
                Error(e)
            }
        }

    override suspend fun getVideosByMovieId(movieId: Int): Result<List<DbVideo>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(videosDao.getVideosByMovie(movieId))
            } catch (e: Exception) {
                Error(e)
            }
        }

    override suspend fun update(movie: DbMovie) = withContext(ioDispatcher) {
        moviesDao.update(movie)
    }
}