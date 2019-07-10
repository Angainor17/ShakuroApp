package com.voronin.shakuro.contributorsList.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voronin.shakuro.contributorsList.models.Contributor


class ContributorListAdapter : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {

    private val items: ArrayList<Contributor> = ArrayList()

    fun update(items: ArrayList<Contributor>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}