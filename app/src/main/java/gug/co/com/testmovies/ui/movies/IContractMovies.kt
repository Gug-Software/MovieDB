package gug.co.com.testmovies.ui.movies

import gug.co.com.testmovies.data.domain.Movie
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter

interface IContractMovies {

    interface View {

        /**
         * Navigate to movie detail
         * @param movieId movie id for the detail
         */
        fun navigateToMovieDetail(movieId: Int)
    }

    interface ViewModel {

        fun loadMovies(moviesFilter: MoviesFilter)

        fun showMovieDetail(movie: Movie)

    }

    interface Model {

        suspend fun getMoviesByFilter(moviesFilter: MoviesFilter): Result<List<DbMovie>>

    }

}