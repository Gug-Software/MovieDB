package gug.co.com.testmovies.di

import gug.co.com.testmovies.data.source.local.MoviesLocalDataStore
import gug.co.com.testmovies.data.source.local.room.MoviesDatabase
import gug.co.com.testmovies.data.source.local.room.MoviesRoomLocalDataStore
import gug.co.com.testmovies.data.source.local.room.dao.MoviesDao
import gug.co.com.testmovies.data.source.remote.MoviesRemoteDataStore
import gug.co.com.testmovies.data.source.remote.retrofit.MoviesAPI
import gug.co.com.testmovies.data.source.remote.retrofit.MoviesRetrofit
import gug.co.com.testmovies.data.source.remote.retrofit.MoviesRetrofitRemoteDataStore
import gug.co.com.testmovies.repository.movies.MoviesRepository
import gug.co.com.testmovies.ui.movies.IContractMovies
import gug.co.com.testmovies.viewmodels.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MoviesModule {

    companion object {

        val appModule = module {

            // Retrofit instance for moviesApi remote
            single<MoviesAPI> { MoviesRetrofit.moviesApi }
            // MoviesDao from local database
            single<MoviesDao> { MoviesDatabase.getDatabase(get()).moviesDao() }

            // Remote Data store with retrofit movies api
            single<MoviesRemoteDataStore> { MoviesRetrofitRemoteDataStore(get()) }
            // Local Data Store with Room movies dao
            single<MoviesLocalDataStore> { MoviesRoomLocalDataStore(get()) }

            // Movies Repository with Remote Retrofit and Local Room Dao
            single<IContractMovies.Model> { MoviesRepository(get(), get()) }

            // define SplashViewModel
            viewModel { MoviesViewModel(get()) }

        }


    }
}