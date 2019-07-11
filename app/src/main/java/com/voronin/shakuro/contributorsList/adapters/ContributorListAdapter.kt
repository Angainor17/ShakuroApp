package com.voronin.shakuro.contributorsList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voronin.shakuro.R
import com.voronin.shakuro.contributorsList.models.Contributor
import com.voronin.shakuro.databinding.ContributorListItemBinding

class ContributorListAdapter : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {

    private val items: ArrayList<Contributor> = ArrayList()
    var onClickListener: ((contributor: Contributor) -> Unit)? = null

    fun update(items: ArrayList<Contributor>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ContributorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.contributor = item
        holder.background.setOnClickListener { onClickListener?.invoke(item) }
    }

    class ViewHolder(val binding: ContributorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val background: View = binding.root.findViewById(R.id.background)
    }
}