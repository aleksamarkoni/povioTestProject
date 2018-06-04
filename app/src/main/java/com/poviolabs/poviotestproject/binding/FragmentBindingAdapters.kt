package com.poviolabs.poviotestproject.binding

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.poviolabs.poviotestproject.R
import javax.inject.Inject

/**
 * Binding adapters that work with a fragment instance.
 */
class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(fragment).load(url).into(imageView)
    }

    @BindingAdapter("sightings")
    fun bindSightings(textView: TextView, sightings: Int) {
        when (sightings) {
            0 -> textView.text = fragment.resources.getString(R.string.no_sightings)
            else -> textView.text = fragment.resources.getQuantityString(R.plurals.sightings, sightings, sightings)
        }
    }
}
