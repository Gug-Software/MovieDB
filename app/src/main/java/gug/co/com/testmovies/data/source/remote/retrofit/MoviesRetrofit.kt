package gug.co.com.testmovies.data.source.remote.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val URL = "https://api.themoviedb.org/3/"
const val API_KEY = "f6f22d6270ecc338f31fb3771cc6b13e"

const val LANGUAGE_ES = "ES"
const val REGION_COLOMBIA = "CO"

const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(20, TimeUnit.SECONDS)
    .writeTimeout(20, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .build()

/**
 * Main entry point for network access. Call like `Network.devPosts.getPlaylist()`
 */
object MoviesRetrofit {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val moviesApi = retrofit.create(MoviesAPI::class.java)

}