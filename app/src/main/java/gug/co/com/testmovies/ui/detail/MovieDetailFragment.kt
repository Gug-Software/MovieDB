package gug.co.com.testmovies.ui.detail

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import gug.co.com.testmovies.R
import gug.co.com.testmovies.databinding.FragmentMovieDetailBinding
import gug.co.com.testmovies.ui.detail.adapter.genre.GenreItemAdapter
import gug.co.com.testmovies.ui.detail.adapter.production_company.ProductionCompanyItemAdapter
import gug.co.com.testmovies.ui.detail.adapter.spoken_language.SpokenLanguageItemAdapter
import gug.co.com.testmovies.ui.detail.adapter.video.MovieVideoAdapter
import gug.co.com.testmovies.ui.detail.adapter.video.MovieVideoItemListener
import gug.co.com.testmovies.utils.movies.MoviesFilter
import gug.co.com.testmovies.viewmodels.moviedetail.MovieDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailBinding
    lateinit var application: Application
    lateinit var moviesFilter: MoviesFilter
    var movieId: Int = 0

    // Lazy inject ViewModel
    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false
        )

        application = requireNotNull(value = this.activity).application

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        configureRecyclers()
        defineObservers()

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        val args = MovieDetailFragmentArgs.fromBundle(arguments!!)
        movieId = args.movieId
        moviesFilter = args.filter
        viewModel.loadMovie(args.movieId, moviesFilter)
    }

    private fun configureRecyclers() {

        configureRecyclerGenres()
        configureRecyclerProductionCompanies()
        configureRecyclerSpokenLanguages()
        configureRecyclerMovieVideos()

    }

    private fun configureRecyclerMovieVideos() {

        val adapter = MovieVideoAdapter(
            MovieVideoItemListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.videoUrl)))
            }
        )
        binding.videosRecycler.adapter = adapter
        viewModel.videos.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun configureRecyclerSpokenLanguages() {

        val adapter = SpokenLanguageItemAdapter()
        binding.spokenLanguagesRecycler.adapter = adapter
        viewModel.spokenLanguages.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun configureRecyclerProductionCompanies() {

        val adapter = ProductionCompanyItemAdapter()
        binding.productionCompaniesRecycler.adapter = adapter
        viewModel.productionCompanies.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun configureRecyclerGenres() {

        val adapter = GenreItemAdapter()
        binding.genresRecycler.adapter = adapter
        viewModel.genres.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun defineObservers() {

    }


}
