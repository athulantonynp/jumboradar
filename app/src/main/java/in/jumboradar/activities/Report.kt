package `in`.jumboradar.activities

import `in`.jumboradar.R
import `in`.jumboradar.modules.LocationModule
import `in`.jumboradar.utils.Constants
import `in`.jumboradar.utils.Toasty
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_report.*

class Report : AppCompatActivity(), LocationListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        supportActionBar!!.apply {
            title="Report"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }

        onReportClick()
    }

    private fun onReportClick() {

        if(LocationModule.isPermissionRequestNeeded()){
            LocationModule.requestPermission(this,this)
        }else{
            LocationModule.requestForLocation(this,this)
        }
    }

    fun onChooseMapClick(view: View){
        startActivityForResult(Intent(Report@this,History::class.java),1234)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
    fun onSubmitClick(view: View){
        Toasty.show(this,"Successfully submitted.Thank you!")
        finish()
    }

    override fun onLocationChanged(location: Location?) {
        if(location!=null){
            Log.e("LOCATION", location!!.longitude.toString()+ " "+location!!.latitude.toString())
            et_location.setText(location!!.longitude.toString()+ ","+location!!.latitude.toString())
        }

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1234){

            if(resultCode== Activity.RESULT_OK){
                et_location.setText(data!!.getStringExtra("location"))
            }
        }
    }
}
