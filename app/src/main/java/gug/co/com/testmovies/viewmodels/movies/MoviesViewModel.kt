package gug.co.com.testmovies.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gug.co.com.testmovies.R
import gug.co.com.testmovies.data.domain.Movie
import gug.co.com.testmovies.data.source.local.room.entities.asDomainModel
import gug.co.com.testmovies.data.source.remote.NetworkApiStatus
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

    private val _status = MutableLiveData<NetworkApiStatus>()
    val status: LiveData<NetworkApiStatus> = _status

    private val _navToDetailMovie = MutableLiveData<Movie>()
    val navToDetailMovie: LiveData<Movie> = _navToDetailMovie

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    private val _moviesFromRepo = MutableLiveData<List<Movie>>().apply { value = emptyList() }

    override fun loadMovies(moviesFilter: MoviesFilter) {

        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                val moviesFromRepository = moviesRepository.getMoviesByFilter(moviesFilter)
                if (moviesFromRepository is Result.Success) {
                    _movies.value = ArrayList(moviesFromRepository.data.asDomainModel())
                    _moviesFromRepo.value = _movies.value
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

    override fun resetSearch() {
        _movies.value = _moviesFromRepo.value
    }

    override fun filterMoviesByQuery(
        query: String,
        moviesFilter: MoviesFilter,
        isGlobal: Boolean
    ) {

        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                val moviesSearchFromRepository =
                    moviesRepository.searchMoviesByQueryAndFilter(query, moviesFilter, isGlobal)
                if (moviesSearchFromRepository is Result.Success) {
                    _movies.value = ArrayList(moviesSearchFromRepository.data.asDomainModel())
                    if (_movies.value.isNullOrEmpty()) {
                        _status.value = NetworkApiStatus.ERROR
                    } else {
                        _status.value = NetworkApiStatus.DONE
                    }
                } else {
                    _status.value = NetworkApiStatus.ERROR
                    _movies.value = emptyList()
                    _snackbarText.value = R.string.msg_error
                }
            } catch (e: Exception) {
                _status.value = NetworkApiStatus.ERROR
                _movies.value = emptyList()
                _snackbarText.value = R.string.msg_error
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