package com.poviolabs.poviotestproject.ui.search

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.poviolabs.poviotestproject.AppExecutors
import com.poviolabs.poviotestproject.R
import com.poviolabs.poviotestproject.databinding.RepoItemBinding
import com.poviolabs.poviotestproject.models.Flower

/**
 * A RecyclerView adapter for [Repo] class.
 */
class FlowerListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val repoClickCallback: ((Flower) -> Unit)?
) : DataBoundListAdapter<Flower, RepoItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Flower>() {
            override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.name == newItem.name
                        && oldItem.latinName == newItem.latinName
                        && oldItem.sightings == newItem.sightings
                        && oldItem.profilePicture == newItem.profilePicture
                        && oldItem.favorite == newItem.favorite
            }
        }
) {

    override fun createBinding(parent: ViewGroup): RepoItemBinding {
        val binding = DataBindingUtil.inflate<RepoItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.repo_item,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.flower?.let {
                repoClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: RepoItemBinding, item: Flower) {
        binding.flower = item
    }
}
