package `in`.jumboradar.activities

import `in`.jumboradar.R
import `in`.jumboradar.modules.LocationModule
import `in`.jumboradar.utils.Constants
import `in`.jumboradar.utils.Toasty
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApi
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity(), LocationListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnHistory.setOnClickListener { onHistoryClick() }
        btnReport.setOnClickListener { onReportClick() }

    }

    private fun onReportClick() {

        if(LocationModule.isPermissionRequestNeeded()){
            LocationModule.requestPermission(this,this)
        }else{
            LocationModule.requestForLocation(this,this)
        }
    }

    private fun onHistoryClick() {
        startActivity(Intent(Home@this,History::class.java))
    }




    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

    override fun onLocationChanged(location: Location?) {

        Log.e("LOCATION", location!!.longitude.toString()+ " "+location!!.latitude.toString())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    LocationModule.requestForLocation(this,this)
                } else {
                  Toasty.show(this,"Cannot fetch location without permission")
                }

            }
        }
    }
}
