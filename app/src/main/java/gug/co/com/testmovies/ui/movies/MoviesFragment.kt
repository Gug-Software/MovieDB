package gug.co.com.testmovies.ui.movies

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import gug.co.com.testmovies.R
import gug.co.com.testmovies.databinding.FragmentMoviesBinding
import gug.co.com.testmovies.ui.movies.adapter.MovieItemListener
import gug.co.com.testmovies.ui.movies.adapter.MoviesAdapter
import gug.co.com.testmovies.utils.movies.MoviesFilter
import gug.co.com.testmovies.viewmodels.movies.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), IContractMovies.View {

    lateinit var binding: FragmentMoviesBinding
    lateinit var application: Application
    lateinit var moviesFilter: MoviesFilter
    var isGlobal = false

    // Lazy inject ViewModel
    private val viewModel by viewModel<MoviesViewModel>()
    val coroutineContext = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies, container, false
        )

        application = requireNotNull(value = this.activity).application

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        configureRecyclerMovies()
        defineObservers()
        defineTextWatcherForSearch()

        return binding.root
    }

    private fun defineTextWatcherForSearch() {

        var searchJob: Job? = null

        val watcher = object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val searchText = s.toString().trim()

                searchJob?.cancel()
                searchJob = coroutineContext.launch {
                    delay(500)  //debounce timeOut
                    if (searchText.isEmpty()) {
                        viewModel.resetSearch()
                    } else {
                        viewModel.filterMoviesByQuery(searchText, moviesFilter, isGlobal)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        }

        binding.filterInputText.addTextChangedListener(watcher)

    }

    override fun onStart() {

        super.onStart()

        moviesFilter = MoviesFragmentArgs.fromBundle(arguments!!).filter
        isGlobal = MoviesFragmentArgs.fromBundle(arguments!!).isGlobal
        if (!isGlobal) {
            viewModel.loadMovies(moviesFilter)
        } else {
            setHasOptionsMenu(false)
        }

        if (!filter_inputText.text?.isEmpty()!!) {
            viewModel.filterMoviesByQuery(filter_inputText.text.toString(), moviesFilter, isGlobal)
        }

    }

    private fun configureRecyclerMovies() {

        val adapter = MoviesAdapter(
            MovieItemListener {
                viewModel.showMovieDetail(it)
            }
        )

        binding.moviesRecycler.adapter = adapter

        viewModel.movies.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun defineObservers() {

        viewModel.navToDetailMovie.observe(this, Observer { movie ->
            movie?.let {
                navigateToMovieDetail(movie.id)
                viewModel.onMovieDetailNavigated()
            }
        })

    }

    override fun navigateToMovieDetail(movieId: Int) {
        this.findNavController().navigate(
            MoviesFragmentDirections.actionGlobalMovieDetail(movieId, moviesFilter)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_overflow_movies, menu)
    }
}
