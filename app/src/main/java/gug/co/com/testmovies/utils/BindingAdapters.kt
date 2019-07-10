package gug.co.com.testmovies.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import gug.co.com.testmovies.R
import gug.co.com.testmovies.data.domain.Movie
import gug.co.com.testmovies.data.source.remote.NetworkApiStatus

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

}

@BindingAdapter("showFromApiStatus")
fun showFromApiStatus(progressBar: ProgressBar, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING -> progressBar.visibility = View.VISIBLE
        else -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("hideFromApiStatus")
fun hideFromApiStatus(view: View, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING, NetworkApiStatus.DONE -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("showDataFromApiStatus")
fun showDataFromApiStatus(view: View, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING, NetworkApiStatus.DONE -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("hideIsEmptyString")
fun hideIsEmptyString(view: View, string: String) {
    if (string.isEmpty()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("showIfZero")
fun showIfZero(view: View, moviesSize: Int) {
    when (moviesSize) {
        0 -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view: View, moviesSize: Int) {
    when (moviesSize) {
        0 -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("runtimeMinutes")
fun TextView.setRuntimeMinutes(movie: Movie) {
    when (movie.runtime) {
        0 -> visibility = View.GONE
        else -> {
            visibility = View.VISIBLE
            if (movie.runtime != null) {
                setText(context.resources.getString(R.string.text_durationMinutes, movie.runtime.toString()))
            } else {
                visibility = View.GONE
            }

        }
    }
}