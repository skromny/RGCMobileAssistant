package com.recrutify.rgc.mobileassistant.projects

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.recrutify.rgc.mobileassistant.R
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTime
import javax.inject.Inject

class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(fragment).load(url).into(imageView)
    }

    @BindingAdapter("app:projectStatusText")
    fun setStatusTextResource(textView: TextView, resource: Int) {
        Log.d("FragmentBindingAdapters", "RESID: ${resource}")
        if(textView.id == R.id.tv_project_status){
            when(resource){
                1 -> textView.setText("Aktywny")
                2 -> textView.setText("Zawieszony")
                3 -> textView.setText("Zamknięty")
                4 -> textView.setText("Zatrudnienie")
                5 -> textView.setText("Anulowany")
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

    @BindingAdapter("app:projectStatusIcon")
    fun setStatusImageResource(imageView: ImageView, resource: Int) {
        Log.d("FragmentBindingAdapters", "RESID: ${resource}")
        when(resource){
            1 -> imageView.setImageResource(R.drawable.ic_in_progress)
            2 -> imageView.setImageResource(R.drawable.ic_suspended)
            3 -> imageView.setImageResource(R.drawable.ic_closed)
            4 -> imageView.setImageResource(R.drawable.ic_hired)
            5 -> imageView.setImageResource(R.drawable.ic_cancel)
        }

    }

}
