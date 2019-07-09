package gug.co.com.testmovies.ui.detail.adapter.spoken_language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gug.co.com.testmovies.data.domain.SpokenLanguage
import gug.co.com.testmovies.databinding.RecyclerItemLanguageBinding

class SpokenLanguageItemAdapter :
    ListAdapter<SpokenLanguage, SpokenLanguageItemAdapter.CompanyViewHolder>(
        LanguageDiffCallback()
    ) {

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder.from(
            parent
        )
    }

    class CompanyViewHolder private constructor(
        val binding: RecyclerItemLanguageBinding
    ) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SpokenLanguage) {
            binding.language = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CompanyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemLanguageBinding.inflate(layoutInflater, parent, false)

                return CompanyViewHolder(
                    binding
                )
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
class LanguageDiffCallback : DiffUtil.ItemCallback<SpokenLanguage>() {
    override fun areItemsTheSame(oldItem: SpokenLanguage, newItem: SpokenLanguage): Boolean {
        return oldItem.iso6391 == newItem.iso6391
    }

    override fun areContentsTheSame(oldItem: SpokenLanguage, newItem: SpokenLanguage): Boolean {
        return oldItem == newItem
    }
}