package gug.co.com.testmovies.di

import gug.co.com.testmovies.data.source.local.MovieDetailLocalDataStore
import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.local.room.MovieDetailRoomLocalDataStore
import gug.co.com.testmovies.data.source.local.room.MoviesDatabase
import gug.co.com.testmovies.data.source.local.room.MoviesRoomLocalDataStore
import gug.co.com.testmovies.data.source.local.room.dao.*
import gug.co.com.testmovies.data.source.remote.MovieDetailRemoteDataStore
import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.MovieDetailRetrofitRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.MoviesAPI
import gug.co.com.testmovies.data.source.remote.retrofit.MoviesRetrofit
import gug.co.com.testmovies.data.source.remote.retrofit.MoviesRetrofitRemoteDataStore
import gug.co.com.testmovies.repository.detail.MovieDetailRepository
import gug.co.com.testmovies.repository.movies.MoviesRepository
import gug.co.com.testmovies.ui.detail.IContractMovieDetail
import gug.co.com.testmovies.ui.movies.IContractMovies
import gug.co.com.testmovies.viewmodels.moviedetail.MovieDetailViewModel
import gug.co.com.testmovies.viewmodels.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MoviesModule {

    companion object {

        val appModule = module {

            // Retrofit instance for moviesApi remote
            single<MoviesAPI> { MoviesRetrofit.moviesApi }

            // Daos from local database
            single<MoviesDao> { MoviesDatabase.getDatabase(get()).moviesDao() }
            single<GenreDao> { MoviesDatabase.getDatabase(get()).genreDao() }
            single<SpokenLanguageDao> { MoviesDatabase.getDatabase(get()).spokenLanguageDao() }
            single<ProductionCompanyDao> { MoviesDatabase.getDatabase(get()).productionCompanyDao() }
            single<VideosDao> { MoviesDatabase.getDatabase(get()).videosDao() }

            // Remote Data store with retrofit movies api
            single<MoviesRemoteDataStore> { MoviesRetrofitRemoteDataStore(get()) }
            // Local Data Store with Room movies dao
            single<MoviesLocalDataStore> { MoviesRoomLocalDataStore(get()) }

            // Remote Data store with retrofit movies api
            single<MovieDetailRemoteDataStore> { MovieDetailRetrofitRemoteDataStore(get()) }
            // Local Data Store with Room movies dao
            single<MovieDetailLocalDataStore> { MovieDetailRoomLocalDataStore(get(), get(), get(), get(), get()) }

            // Movies Repository with Remote Retrofit and Local Room Dao
            single<IContractMovies.Model> { MoviesRepository(get(), get()) }

            // Movie Detail Repository with Remote Retrofit and Local Room Dao
            single<IContractMovieDetail.Model> { MovieDetailRepository(get(), get()) }

            // define MoviesViewModel
            viewModel { MoviesViewModel(get()) }
            // define MovieDetailViewModel
            viewModel { MovieDetailViewModel(get()) }

        }

    }
}