package gug.co.com.testmovies.ui.detail

import gug.co.com.testmovies.data.source.local.room.entities.DbGenre
import gug.co.com.testmovies.data.source.local.room.entities.DbMovie
import gug.co.com.testmovies.data.source.local.room.entities.DbProductionCompany
import gug.co.com.testmovies.data.source.local.room.entities.DbSpokenLanguage
import gug.co.com.testmovies.utils.Result

interface IContractMovieDetail {

    interface View {

    }

    interface ViewModel {

    }

    interface Model {

        suspend fun getMovieDetailById(movieId: Int): Result<DbMovie>

        suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>>

        suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>>

        suspend fun getSpokenLanguagesByMovieId(movieId: Int): Result<List<DbSpokenLanguage>>

    }

}