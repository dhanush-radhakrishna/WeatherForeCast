package com.cg.weatherforecast

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_days_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A fragment representing a list of Items.
 */
class DaysFragment : Fragment() {

    private var columnCount = 1
    private var lat:Double=0.0
    private var long:Double=0.0
   lateinit var rview: RecyclerView
     var data : DataWeather? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            lat=it.getDouble("lat")
            long=it.getDouble("long")
        }


        val exclude="current,alerts,minutely,hourly"
        val units="metric"
        val apikey="9496e40cc0aa96b1e8ca1666f835127c"
        val request=getWeather.getInstance().getdailyData("$lat","$long",exclude,apikey,units)
        request.enqueue(getWeatherDataCallback())

    }
    inner class getWeatherDataCallback:Callback<DataWeather> {
        override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
            if(response.isSuccessful){
                data= response.body()!!
                Log.d("retroData","Message: $data")
                data?.let {
                    val list=it.daily
                    rview.adapter=WeatherViewAdapter(list)
                }

            }
        }

        override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        rview = inflater.inflate(R.layout.fragment_days_list, container, false) as RecyclerView

        // Set the adapter
        if (rview is RecyclerView) {
            with(rview) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = data?.let { WeatherViewAdapter(it.daily) }
            }
        }
        return rview
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int,latitude:Double,longitude:Double) =
            DaysFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putDouble("lat",latitude)
                    putDouble("long",longitude)
                }
            }
    }

}