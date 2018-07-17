package `in`.jumboradar.activities

import `in`.jumboradar.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Glide.with(this).load(R.drawable.ic_app_logo_gif).into(iv_app_logo)
        Handler().postDelayed({ goToHome() },3000)
    }

    private fun goToHome() {
        startActivity(Intent(this,Home::class.java))
        finish()
    }
}
