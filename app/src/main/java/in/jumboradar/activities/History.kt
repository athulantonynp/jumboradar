package `in`.jumboradar.activities

import `in`.jumboradar.R
import `in`.jumboradar.utils.Utils
import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class History : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMapClickListener{


    private lateinit var mMap: GoogleMap
    var lat:String=""
    var long:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.apply {
            title="Select Location"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }
        setContentView(R.layout.activity_history)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val gudalur = LatLng(11.50775, 76.4711862)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(Utils.getElephantMarker().position(gudalur).title("Marker somewhere"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(gudalur.latitude, gudalur.longitude), 15.0f))
        mMap.setOnMapClickListener(this)

    }

    override fun onMapClick(latLng: LatLng?) {

        if(latLng!=null){

            mMap.clear()
            mMap.addMarker(Utils.getElephantMarker().position(latLng).title("You selected here."))
            lat=latLng.latitude.toString()
            long=latLng.longitude.toString()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.select_area,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId==R.id.action_done){
            onFinishMaps()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onFinishMaps() {
        intent.putExtra("location",long+","+lat)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }


}
