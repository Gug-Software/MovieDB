package gug.co.com.testmovies.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import gug.co.com.testmovies.data.source.remote.retrofit.NetworkApiStatus

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

}

@BindingAdapter("postsApiStatus")
fun postsApiStatus(statusTextView: TextView, status: NetworkApiStatus?) {
    when (status) {
        NetworkApiStatus.LOADING, NetworkApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
        }
        NetworkApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
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
        NetworkApiStatus.LOADING -> view.visibility = View.GONE
        NetworkApiStatus.ERROR -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}