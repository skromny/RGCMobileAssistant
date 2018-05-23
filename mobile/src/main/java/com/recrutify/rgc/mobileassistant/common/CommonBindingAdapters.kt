package com.recrutify.rgc.mobileassistant.common

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.recrutify.rgc.mobileassistant.Model.Label
import com.recrutify.rgc.mobileassistant.R
import org.joda.time.DateTime
import javax.inject.Inject

class CommonBindingAdapters @Inject constructor(val fragment: Fragment, val labelsAdapter: LabelsAdapter) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(fragment).load(url).into(imageView)
    }

    @BindingAdapter("app:normalizedDateTime")
    fun setDateTimeText(textView: TextView, resource: String) {
        Log.d("FragmentBindingAdapters", "RESID: ${resource}")
        if(textView.id == R.id.tv_creation_date) {
            val dt = DateTime.parse(resource)
            textView.setText(dt.toString("yyyy-MM-dd HH:mm:ss"))
        }
        else
            textView.setText(resource)
    }

    @BindingAdapter("app:labelsList")
    fun setLabels(recyclerView: RecyclerView, resource: List<Label>) {
        Log.d("labelsList", "RESID: ${resource.size}")

        labelsAdapter.updateList(resource)
        recyclerView.adapter = labelsAdapter
    }
}