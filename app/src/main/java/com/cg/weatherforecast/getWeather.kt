package com.cg.weatherforecast

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface getWeather {

    @GET("onecall")
    fun getdailyData( @Query("lat") lat: String,
                      @Query("lon")lon:String,
                      @Query("exclude")exclude:String,
                      @Query("appid")appid:String,
                      @Query("units")units:String,): Call<DataWeather>
    @GET("onecall")
    fun getCurrentData(@Query("city name")city:String,
                      @Query("exclude")exclude:String,
                      @Query("appid")appid:String,
                      @Query("units")units:String,): Call<DataWeather>

    companion object{
        val  baseURL="https://api.openweathermap.org/data/2.5/"
        fun getInstance():getWeather{
            val retrofit= Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL).build()
            return retrofit.create(getWeather::class.java)
        }
    }
}