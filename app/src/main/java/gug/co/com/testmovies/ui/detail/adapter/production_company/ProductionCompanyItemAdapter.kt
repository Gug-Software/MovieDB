package gug.co.com.testmovies.ui.detail.adapter.production_company

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gug.co.com.testmovies.data.domain.ProductionCompany
import gug.co.com.testmovies.databinding.RecyclerItemProductioncompanyBinding

class ProductionCompanyItemAdapter() :
    ListAdapter<ProductionCompany, ProductionCompanyItemAdapter.CompanyViewHolder>(
        CompanyDiffCallback()
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
        val binding: RecyclerItemProductioncompanyBinding
    ) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductionCompany) {
            binding.company = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CompanyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemProductioncompanyBinding.inflate(layoutInflater, parent, false)

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
class CompanyDiffCallback : DiffUtil.ItemCallback<ProductionCompany>() {
    override fun areItemsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany): Boolean {
        return oldItem == newItem
    }
}