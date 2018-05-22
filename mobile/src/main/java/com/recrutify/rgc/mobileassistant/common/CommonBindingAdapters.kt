package com.recrutify.rgc.mobileassistant.common

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.recrutify.rgc.mobileassistant.R
import org.joda.time.DateTime
import javax.inject.Inject

class CommonBindingAdapters @Inject constructor(val fragmant: Fragment){

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
}