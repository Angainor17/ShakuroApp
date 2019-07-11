package com.voronin.shakuro.contributorsList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voronin.shakuro.R
import com.voronin.shakuro.contributorsList.models.Contributor
import com.voronin.shakuro.databinding.ContributorListItemBinding

class ContributorListAdapter(diffCallback: DiffUtil.ItemCallback<Contributor>) :
    PagedListAdapter<Contributor, ContributorListAdapter.ViewHolder>(diffCallback) {

    var onClickListener: ((contributor: Contributor) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ContributorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.contributor = item
        holder.background.setOnClickListener { onClickListener?.invoke(item!!) }
    }

    class ViewHolder(val binding: ContributorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val background: View = binding.root.findViewById(R.id.background)
    }
}