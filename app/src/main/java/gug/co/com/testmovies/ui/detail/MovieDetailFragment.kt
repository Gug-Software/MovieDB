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
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.google.android.material.snackbar.Snackbar
import gug.co.com.testmovies.R
import gug.co.com.testmovies.data.source.remote.NetworkApiStatus
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

    // recyclers adapters
    lateinit var adapterVideos: MovieVideoAdapter
    lateinit var adapterGenres: GenreItemAdapter
    lateinit var adapterProductionCompanies: ProductionCompanyItemAdapter
    lateinit var adapterSpokenLanguages: SpokenLanguageItemAdapter

    // for skeleton animation
    lateinit var skeletonRecyclerVideos: RecyclerViewSkeletonScreen
    lateinit var skeletonRecyclerGenres: RecyclerViewSkeletonScreen
    lateinit var skeletonRecyclerCompanies: RecyclerViewSkeletonScreen
    lateinit var skeletonRecyclerLanguages: RecyclerViewSkeletonScreen

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
        defineSkeletonScreens()
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

        adapterVideos = MovieVideoAdapter(
            MovieVideoItemListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.videoUrl)))
            }
        )
        binding.videosRecycler.adapter = adapterVideos
        viewModel.videos.observe(this, Observer {
            adapterVideos.submitList(it)
        })

    }

    private fun configureRecyclerSpokenLanguages() {

        adapterSpokenLanguages = SpokenLanguageItemAdapter()
        binding.spokenLanguagesRecycler.adapter = adapterSpokenLanguages
        viewModel.spokenLanguages.observe(this, Observer {
            adapterSpokenLanguages.submitList(it)
        })

    }

    private fun configureRecyclerProductionCompanies() {

        adapterProductionCompanies = ProductionCompanyItemAdapter()
        binding.productionCompaniesRecycler.adapter = adapterProductionCompanies
        viewModel.productionCompanies.observe(this, Observer {
            adapterProductionCompanies.submitList(it)
        })

    }

    private fun configureRecyclerGenres() {

        adapterGenres = GenreItemAdapter()
        binding.genresRecycler.adapter = adapterGenres
        viewModel.genres.observe(this, Observer {
            adapterGenres.submitList(it)
        })

    }

    private fun defineSkeletonScreens() {

        skeletonRecyclerVideos = Skeleton.bind(binding.videosRecycler)
            .adapter(adapterVideos)
            .load(R.layout.recycler_item_movievideo)
            .color(R.color.primaryLightColor)
            .show()

        skeletonRecyclerLanguages = Skeleton.bind(binding.spokenLanguagesRecycler)
            .adapter(adapterSpokenLanguages)
            .load(R.layout.recycler_item_language)
            .color(R.color.primaryLightColor)
            .show()

        skeletonRecyclerCompanies = Skeleton.bind(binding.productionCompaniesRecycler)
            .adapter(adapterProductionCompanies)
            .load(R.layout.recycler_item_productioncompany)
            .color(R.color.primaryLightColor)
            .show()

        skeletonRecyclerGenres = Skeleton.bind(binding.genresRecycler)
            .adapter(adapterGenres)
            .load(R.layout.recycler_item_genre)
            .color(R.color.primaryLightColor)
            .show()

    }

    private fun defineObservers() {

        viewModel.status.observe(this, Observer { status ->
            status.let {
                when (status) {
                    NetworkApiStatus.LOADING -> {
                        skeletonRecyclerVideos.show()
                        skeletonRecyclerGenres.show()
                        skeletonRecyclerCompanies.show()
                        skeletonRecyclerLanguages.show()
                    }
                    else -> {
                        skeletonRecyclerVideos.hide()
                        skeletonRecyclerGenres.hide()
                        skeletonRecyclerCompanies.hide()
                        skeletonRecyclerLanguages.hide()
                    }
                }
            }
        })

        viewModel.snackbarMessage.observe(this, Observer {
            Snackbar.make(binding.coordinator, getString(it), Snackbar.LENGTH_LONG).show()
        })

    }

}
