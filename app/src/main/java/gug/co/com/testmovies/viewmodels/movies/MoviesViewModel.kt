package gug.co.com.testmovies.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gug.co.com.testmovies.R
import gug.co.com.testmovies.data.domain.Movie
import gug.co.com.testmovies.data.source.local.room.entities.asDomainModel
import gug.co.com.testmovies.data.source.remote.retrofit.NetworkApiStatus
import gug.co.com.testmovies.ui.movies.IContractMovies
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter
import gug.co.com.testmovies.viewmodels.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class MoviesViewModel(
    val moviesRepository: IContractMovies.Model
) : BaseViewModel(), IContractMovies.ViewModel {

    private val _movies = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies: LiveData<List<Movie>> = _movies

    private val _status = MutableLiveData<NetworkApiStatus>().apply { value = NetworkApiStatus.LOADING }
    val status: LiveData<NetworkApiStatus> = _status

    private val _navToDetailMovie = MutableLiveData<Movie>()
    val navToDetailMovie: LiveData<Movie> = _navToDetailMovie

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    override fun loadMovies(moviesFilter: MoviesFilter) {

        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                val moviesFromRepository = moviesRepository.getMoviesByFilter(moviesFilter)
                if (moviesFromRepository is Result.Success) {
                    _movies.value = ArrayList(moviesFromRepository.data.asDomainModel())
                    _status.value = NetworkApiStatus.DONE
                } else {
                    _status.value = NetworkApiStatus.ERROR
                    _movies.value = emptyList()
                    _snackbarText.value = R.string.msg_error
                }
            } catch (e: Exception) {
                _status.value = NetworkApiStatus.ERROR
            }
        }

    }

    override fun showMovieDetail(movie: Movie) {
        _navToDetailMovie.value = movie
    }

    fun onMovieDetailNavigated() {
        _navToDetailMovie.value = null
    }


}