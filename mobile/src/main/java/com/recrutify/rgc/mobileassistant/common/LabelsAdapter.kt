package com.recrutify.rgc.mobileassistant.common

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recrutify.rgc.mobileassistant.Model.Label
import kotlinx.android.synthetic.main.label_item.view.*
import com.recrutify.rgc.mobileassistant.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LabelsAdapter @Inject constructor() : RecyclerView.Adapter<LabelsAdapter.ViewHolder>() {

    private var list = emptyList<Label>()

    fun updateList(_list: List<Label>) {
        list = _list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.label_item, parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bindItems(list[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Label) {
            itemView.item_view.text=item.name
            itemView.label.setColorFilter(Color.parseColor(item.color))
        }

    }
}
