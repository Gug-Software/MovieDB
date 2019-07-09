package gug.co.com.testmovies.viewmodels.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gug.co.com.testmovies.R
import gug.co.com.testmovies.data.domain.*
import gug.co.com.testmovies.data.source.local.room.entities.asDomainModel
import gug.co.com.testmovies.data.source.local.room.entities.asDomainModelDetail
import gug.co.com.testmovies.data.source.remote.retrofit.NetworkApiStatus
import gug.co.com.testmovies.ui.detail.IContractMovieDetail
import gug.co.com.testmovies.utils.Result
import gug.co.com.testmovies.utils.movies.MoviesFilter
import gug.co.com.testmovies.viewmodels.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    val repository: IContractMovieDetail.Model
) : BaseViewModel(), IContractMovieDetail.ViewModel {

    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail> = _movie

    private val _genres = MutableLiveData<List<Genre>>().apply { value = emptyList() }
    val genres: LiveData<List<Genre>> = _genres

    private val _productionCompanies = MutableLiveData<List<ProductionCompany>>().apply {
        value = emptyList()
    }
    val productionCompanies: LiveData<List<ProductionCompany>> = _productionCompanies

    private val _spokenLanguages = MutableLiveData<List<SpokenLanguage>>().apply {
        value = emptyList()
    }
    val spokenLanguages: LiveData<List<SpokenLanguage>> = _spokenLanguages

    private val _videos = MutableLiveData<List<MovieVideo>>().apply {
        value = emptyList()
    }
    val videos: LiveData<List<MovieVideo>> = _videos

    private val _status = MutableLiveData<NetworkApiStatus>()
    val status: LiveData<NetworkApiStatus> = _status

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    override fun loadMovie(movieId: Int, moviesFilter: MoviesFilter) {

        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                val moviesFromRepository = repository.getMovieDetailById(movieId, moviesFilter)
                if (moviesFromRepository is Result.Success) {
                    _movie.value = moviesFromRepository.data.asDomainModelDetail()
                    _genres.value = (repository.getGenresByMovieId(movieId) as Result.Success).data.asDomainModel()
                    _productionCompanies.value =
                        (repository.getProductionCompaniesByMovieId(movieId) as Result.Success).data.asDomainModel()
                    _spokenLanguages.value =
                        (repository.getSpokenLanguagesByMovieId(movieId) as Result.Success).data.asDomainModel()
                    _videos.value = (repository.getVideosByMovieId(movieId) as Result.Success).data.asDomainModel()
                    _status.value = NetworkApiStatus.DONE
                } else {
                    _status.value = NetworkApiStatus.ERROR
                    _snackbarText.value = R.string.msg_error
                }
            } catch (e: Exception) {
                _status.value = NetworkApiStatus.ERROR
            }
        }

    }

}