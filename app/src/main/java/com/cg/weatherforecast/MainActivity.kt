package com.cg.weatherforecast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

/*
Weather ForeCast App

Fragment1:
        GPS - current lat/long
        current Location
        city - enter - geoCoder
Fragment2:
        RecyclerView ( 8 days)
        - List of weatherDetails
        - date, min temp, max temp
Fragement3:
        Details weather forecast of that day
        Weather condition
        humidity
        hourly forecast
 */

class MainActivity : AppCompatActivity() {
    lateinit var city :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        city = findViewById(R.id.cityT)

    }

//    override fun onResume() {
//        val listFrag= MapFragment.newInstance("","")
//        supportFragmentManager.beginTransaction().add(R.id.parentL,listFrag).addToBackStack(null).commit()
//        super.onResume()
//    }

    fun bottonClicked(view: View) {
        when(view.id){
            R.id.getWeatherB->{
                val frag= DaysFragment()
                getWeatherB.visibility=View.INVISIBLE
                supportFragmentManager.beginTransaction().add(R.id.parentL,frag).addToBackStack(null).commit()
            }
            R.id.dailyforecastB ->{
                val city = cityT.text.toString()
                val i = Intent(this,DailyForecast::class.java)
                i.putExtra("city",city)
                startActivity(i)
            }
        }

    }


}