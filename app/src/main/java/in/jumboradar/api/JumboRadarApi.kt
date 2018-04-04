package `in`.jumboradar.api

import `in`.jumboradar.beans.SubmitSightingResponse
import retrofit2.http.Field
import retrofit2.http.GET

interface JumboRadarApi {


    @GET("submit/")
    fun submitSighting(@Field("latitude")latitude: String,
                @Field("longitude") longitude:String): retrofit2.Call<SubmitSightingResponse>

    @GET("history/")
    fun getSightings(): retrofit2.Call<SubmitSightingResponse>
}