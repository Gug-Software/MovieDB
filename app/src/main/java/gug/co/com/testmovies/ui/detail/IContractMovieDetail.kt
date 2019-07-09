package gug.co.com.testmovies.ui.detail

import gug.co.com.testmovies.data.source.local.room.entities.*
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter

interface IContractMovieDetail {

    interface View {

    }

    interface ViewModel {

        fun loadMovie(movieId: Int, moviesFilter: MoviesFilter)

    }

    interface Model {

        suspend fun getMovieDetailById(movieId: Int, moviesFilter: MoviesFilter): Result<DbMovie>

        suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>>

        suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>>

        suspend fun getSpokenLanguagesByMovieId(movieId: Int): Result<List<DbSpokenLanguage>>

        suspend fun getVideosByMovieId(movieId: Int): Result<List<DbVideo>>

    }

}