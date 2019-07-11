package com.voronin.shakuro.contributorsList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voronin.shakuro.contributorsList.models.Contributor
import com.voronin.shakuro.databinding.ContributorListItemBinding

class ContributorListAdapter : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {

    private val items: ArrayList<Contributor> = ArrayList()

    fun update(items: ArrayList<Contributor>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ContributorListItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.contributor = items[position]
    }

    class ViewHolder(val binding: ContributorListItemBinding) : RecyclerView.ViewHolder(binding.root)
}