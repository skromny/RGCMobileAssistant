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

    @BindingAdapter("imageUrl", "name")
    fun bindImage(imageView: ImageView, url: String?, name: String) {
        if(url != null)
            Glide.with(fragment).load(url).into(imageView)
        else {
            when(name[0]) {
                'a', 'A', 'j', 'J', 't', 'T' -> imageView.setImageResource(R.color.c1)
                'b', 'B', 'k', 'K', 'u', 'U' -> imageView.setImageResource(R.color.c2)
                'c', 'C', 'l', 'L', 'w', 'W' -> imageView.setImageResource(R.color.c3)
                'd', 'D', 'm', 'M', 'x', 'X' -> imageView.setImageResource(R.color.c4)
                'e', 'E', 'n', 'N', 'y', 'Y' -> imageView.setImageResource(R.color.c5)
                'f', 'F', 'o', 'O', 'z', 'Z' -> imageView.setImageResource(R.color.c6)
                'g', 'G', 'q', 'Q' -> imageView.setImageResource(R.color.c7)
                'h', 'H', 'r', 'R' -> imageView.setImageResource(R.color.c8)
                'i', 'I', 's', 'S' -> imageView.setImageResource(R.color.c9)
                else ->  imageView.setImageResource(R.color.c8)
            }
        }
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