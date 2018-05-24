package com.recrutify.rgc.mobileassistant.candidates

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.recrutify.rgc.mobileassistant.Model.Label
import com.recrutify.rgc.mobileassistant.R
import com.recrutify.rgc.mobileassistant.common.LabelsAdapter
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTime
import javax.inject.Inject

class CandidateBindingAdapters @Inject constructor(val fragment: Fragment) {

    @BindingAdapter("app:sourceName")
    fun setSourcename(textView: TextView, resource: Int?) {
        if(resource == null)
            textView.setText("brak")
        else {
            when(resource) {
                1 -> textView.setText("dodany ręcznie")
                2 -> textView.setText("ręcznie z LinkedIn")
                3 -> textView.setText("LinkedIn")
                4 -> textView.setText("wewn. aplikacja")
            }
        }
    }

    @BindingAdapter("app:sourceIcon")
    fun setStatusImageResource(imageView: ImageView, resource: Int?) {
        //Log.d("FragmentBindingAdapters", "RESID: ${resource}")

        when(resource) {
            1 -> imageView.setImageResource(R.drawable.ic_hand)
            2, 3 -> imageView.setImageResource(R.drawable.ic_linkedin_blue)
            4 -> imageView.setImageResource(R.drawable.ic_inside)
            else -> {
                imageView.setImageResource(R.drawable.ic_arrow_right_dimgreen)
            }
        }

    }
}
