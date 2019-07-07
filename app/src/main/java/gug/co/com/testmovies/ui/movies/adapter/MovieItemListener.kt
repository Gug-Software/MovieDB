package gug.co.com.testmovies.ui.movies.adapter

import gug.co.com.testmovies.data.domain.Movie

class MovieItemListener(
    val clickListener: (movie: Movie) -> Unit
) {

    fun onClick(movie: Movie) = clickListener(movie)

}