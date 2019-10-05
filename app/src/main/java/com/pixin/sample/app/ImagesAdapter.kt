package com.pixin.sample.app

import PackagePixinLib.Pixin
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pintrest_list_item.view.*

class ImagesAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url: String = items.get(position)
        Pixin.instance.load(url, R.drawable.placeholder, holder.ivItemType!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.pintrest_list_item, parent, false))
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val ivItemType = view.tv_image_type
    }

}