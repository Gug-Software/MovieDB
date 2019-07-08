package gug.co.com.testmovies.ui.movies

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), IContractMovies.View {

    lateinit var binding: FragmentMoviesBinding
    lateinit var application: Application
    lateinit var moviesFilter: MoviesFilter

    // Lazy inject ViewModel
    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies, container, false
        )

        application = requireNotNull(value = this.activity).application

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        configureRecyclerMovies()
        defineObservers()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        moviesFilter = MoviesFragmentArgs.fromBundle(arguments!!).filter
        viewModel.loadMovies(moviesFilter)
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


}
