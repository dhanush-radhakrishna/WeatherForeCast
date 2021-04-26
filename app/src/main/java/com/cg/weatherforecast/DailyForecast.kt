package com.cg.weatherforecast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_daily_forecast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyForecast : AppCompatActivity() {
    var city=""
    var data :DataWeather?=null
    lateinit var rview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_forecast)

        val intent = intent
        city = intent.getStringExtra("city").toString()

        rview = findViewById(R.id.recyclerView)

        val exclude="current,alerts,minutely,hourly"
        val units="metric"
        val apikey="9496e40cc0aa96b1e8ca1666f835127c"
        val request =
            getWeather.getInstance().getCurrentData("$city",exclude,apikey,units)
        request.enqueue(getCurrentDataCallback())
    }
    inner class getCurrentDataCallback:Callback<DataWeather>{
        override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
            Toast.makeText(this@DailyForecast,"Response : ${response.body()}",Toast.LENGTH_LONG).show()
            if(response.isSuccessful){
                data = response.body()
                //Toast.makeText(this@DailyForecast,"response success",Toast.LENGTH_LONG).show()
                data?.let{
                    val list = it.daily
                    rview.adapter= WeatherViewAdapter(list)
                }
            }
        }

        override fun onFailure(call: Call<DataWeather>, t: Throwable) {
                Toast.makeText(this@DailyForecast,"no response",Toast.LENGTH_LONG).show()
        }
    }
}