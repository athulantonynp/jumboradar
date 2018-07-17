package `in`.jumboradar.utils

import `in`.jumboradar.R
import android.content.Context
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions

class Utils {

    companion object {

        fun getElephantMarker() : MarkerOptions{
            val icon=BitmapDescriptorFactory.fromResource(R.drawable.ic_maps_marker)
            return MarkerOptions().icon(icon)
        }
    }
}