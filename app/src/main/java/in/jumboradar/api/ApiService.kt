package oldmonk.luvey.api

import `in`.jumboradar.BuildConfig
import `in`.jumboradar.api.JumboRadarApi
import android.app.IntentService
import android.content.Context
import android.content.Intent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by athul on 23/2/18.
 */
class ApiService:IntentService("ApiService"){


    companion object {

        val ACTION_SUBMIT_SIGHTING="ACTION_SUBMIT_SIGHTING"


        fun sendSighting(longitude:String,latitude:String,context: Context){
            val intent = Intent(context,ApiService::class.java)
            intent.action= ACTION_SUBMIT_SIGHTING
            intent.putExtra("EXTRA_LONG",longitude);
            intent.putExtra("EXTRA_LAT",latitude);
            context.startService(intent)
        }
    }




    override fun onHandleIntent(intent: Intent?) {

        if(intent==null){
            return
        }
        val api=getApi()

        if(intent.action.equals(ACTION_SUBMIT_SIGHTING)){

            val longitude=intent.getStringExtra("EXTRA_LONG")
            val latitude=intent.getStringExtra("EXTRA_LAT")
            val call=api.submitSighting(latitude,longitude)
            val response=call.execute().body()
        }
    }

     fun getApi(): JumboRadarApi {

         val client = OkHttpClient().newBuilder()
                 .addInterceptor(HttpLoggingInterceptor().apply {
                     level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                 })
                 .build()
         val retrofit = Retrofit.Builder()
                 .baseUrl("https://us-central1-luvey-4f7f4.cloudfunctions.net/")
                 .client(client)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
        return retrofit.create(JumboRadarApi::class.java)

     }

}