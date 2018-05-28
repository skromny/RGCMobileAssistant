package com.recrutify.rgc.mobileassistant.common

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.FrameLayout
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


    @BindingAdapter("app:normalizedDate")
    fun setDateText(textView: TextView, resource: String?) {
        if(resource != null) {
            Log.d("FragmentBindingAdapters", "RESID: ${resource}")
            val dt = DateTime.parse(resource)
            textView.setText(dt.toString("yyyy-MM-dd"))
        }
    }

    @BindingAdapter("app:normalizedDateTime")
    fun setDateTimeText(textView: TextView, resource: String?) {
        if(resource != null) {
            Log.d("FragmentBindingAdapters", "RESID: ${resource}")
            val dt = DateTime.parse(resource)
            textView.setText(dt.toString("yyyy-MM-dd HH:mm:ss"))
        }
    }

    @BindingAdapter("app:labelsList")
    fun setLabels(recyclerView: RecyclerView, resource: List<Label>) {
        Log.d("labelsList", "RESID: ${resource.size}")

        labelsAdapter.updateList(resource)
        recyclerView.adapter = labelsAdapter
    }

    @BindingAdapter("app:numberToString")
    fun setNumberAsString(textView: TextView, resource: Int) {
        textView.setText(resource.toString())
    }

    @BindingAdapter("app:textIfNotNull")
    fun setTextIfNotNull(textView: TextView, resource: String?) {
        if(resource == null)
            textView.visibility = View.GONE
        else
            textView.setText(resource.toString())
    }

    @BindingAdapter("app:numberIfNotNull")
    fun setTextIfNotNull(textView: TextView, resource: Int?) {
        if(resource == null)
            textView.visibility = View.GONE
        else
            textView.setText(resource.toString())
    }

    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, resource: String?) {
        if(resource.isNullOrBlank())
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }

    @BindingAdapter("android:visibility")
    fun setVisibilityInt(view: View, resource: Int?) {
        if(resource == null)
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }

    @BindingAdapter("app:favourite")
    fun setFavorite(imageView: ImageView, resource: Boolean) {
        if(resource)
            imageView.setImageResource(R.drawable.ic_heart)
        else
            imageView.setImageResource(R.drawable.ic_heart_outline)
    }
}