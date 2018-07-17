package `in`.jumboradar.activities

import `in`.jumboradar.R
import `in`.jumboradar.utils.Toasty
import `in`.jumboradar.utils.Utils
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class Home : AppCompatActivity(), OnMapReadyCallback {



    private lateinit var mMap: GoogleMap
    private lateinit var dialog:MaterialDialog.Builder
    private val list=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    private fun onHistoryClick() {
        startActivity(Intent(Home@this,History::class.java))
    }

     fun onReportSightClick(view: View){
        startActivity(Intent(Home@this,Report::class.java))
    }

     fun onSubscribeClick( view: View){

        dialog= MaterialDialog.Builder(this)
                .title("Select areas where you want to be alerted")
                .items(R.array.subscribe_location_arrays)
                .itemsCallbackMultiChoice(null, { dialog, which, text ->
                    list.add(text.toString())
                })
                .positiveText("Subscribe")
                .negativeText("Cancel")
                .onPositive(MaterialDialog.SingleButtonCallback { dialog, which ->
                    dialog.dismiss()
                    Toasty.show(this,"Successfully subscribed")
                })
                .onNegative(MaterialDialog.SingleButtonCallback { dialog, which ->dialog.dismiss()  })
        dialog.build().show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val gudalur = LatLng(11.50775, 76.4711862)
        val gudalur1 = LatLng(11.4623, 76.483)
        val gudalur2 = LatLng(11.5054, 76.3688)
        val gudalur3 = LatLng(11.4434, 76.4747)
        val gudalur4 = LatLng(11.5003, 76.2738)
        val gudalur5 = LatLng(11.5135, 76.3038)
        mMap.addMarker(Utils.getElephantMarker().position(gudalur))
        mMap.addMarker(Utils.getElephantMarker().position(gudalur1))
        mMap.addMarker(Utils.getElephantMarker().position(gudalur2))
        mMap.addMarker(Utils.getElephantMarker().position(gudalur3))
        mMap.addMarker(Utils.getElephantMarker().position(gudalur4))
        mMap.addMarker(Utils.getElephantMarker().position(gudalur5))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(gudalur.latitude, gudalur.longitude), 10.0f))
    }

}
