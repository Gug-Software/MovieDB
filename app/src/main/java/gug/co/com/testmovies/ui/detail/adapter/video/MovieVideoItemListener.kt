package gug.co.com.testmovies.ui.detail.adapter.video

import gug.co.com.testmovies.data.domain.MovieVideo

class MovieVideoItemListener(
    val clickListener: (movieVideo: MovieVideo) -> Unit
) {

    fun onClick(movieVideo: MovieVideo) = clickListener(movieVideo)

}