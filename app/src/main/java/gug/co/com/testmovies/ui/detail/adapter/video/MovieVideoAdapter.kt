package gug.co.com.testmovies.ui.detail.adapter.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gug.co.com.testmovies.data.domain.MovieVideo
import gug.co.com.testmovies.databinding.RecyclerItemMovievideoBinding

class MovieVideoAdapter(
    val clickListener: MovieVideoItemListener
) :
    ListAdapter<MovieVideo, MovieVideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder.from(
            parent
        )
    }

    class VideoViewHolder private constructor(
        val binding: RecyclerItemMovievideoBinding
    ) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: MovieVideoItemListener, item: MovieVideo) {
            binding.video = item
            binding.listener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): VideoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemMovievideoBinding.inflate(layoutInflater, parent, false)

                return VideoViewHolder(binding)
            }
        }
    }

}


/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class VideoDiffCallback : DiffUtil.ItemCallback<MovieVideo>() {
    override fun areItemsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
        return oldItem == newItem
    }
}