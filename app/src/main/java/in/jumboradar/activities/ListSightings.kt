package `in`.jumboradar.activities

import `in`.jumboradar.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ListSightings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sightings)

        supportActionBar!!.apply {
            title="Past Incidents"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return  false
    }
}
