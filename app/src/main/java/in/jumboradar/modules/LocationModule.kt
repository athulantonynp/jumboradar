package `in`.jumboradar.modules

import `in`.jumboradar.utils.Constants
import `in`.jumboradar.utils.Toasty
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import java.util.logging.Logger


open class LocationModule{

    companion object {

        fun isPermissionRequestNeeded():Boolean{
             return (Build.VERSION.SDK_INT >= 23)
        }

        @SuppressLint("MissingPermission")
        fun requestForLocation(context: Context, listner:LocationListener) {
            try {
                val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val criteria = Criteria()
                criteria.accuracy = Criteria.ACCURACY_FINE
                val provider = locationManager.getBestProvider(criteria, true)
                if (provider != null) {
                    locationManager.requestLocationUpdates(provider, 1000, 1000f, listner)
                } else {
                    Toasty.show(context,"Null provider")
                }
                val location=locationManager.getLastKnownLocation(provider)
                listner.onLocationChanged(location)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

         fun requestPermission(mContext:Activity,listner: LocationListener):Boolean {
            val hasPermissionLocation = ContextCompat.checkSelfPermission(mContext.getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            if (!hasPermissionLocation) {
                ActivityCompat.requestPermissions(mContext,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), Constants.REQUEST_ACCESS_FINE_LOCATION)
                return  true;
            } else {
                requestForLocation(mContext,listner)
               return false
            }
        }

    }
}


