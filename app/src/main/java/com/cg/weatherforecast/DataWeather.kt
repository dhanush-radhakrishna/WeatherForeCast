package com.cg.weatherforecast

import java.util.*

data class DataWeather (
    val timezone_offset:Long,
    val daily:List<Properties>
        )
    data class Properties(val dt:Long,
                          val sunrise:String,
                          val sunset:String,
                          val temp: Temp,
                          val weather:List<Weather>)
    data class Weather(val description: String,
                       val icon:String)
    data class CurrentDayDetails(val dt:Long,
                                 val sunrise:String,
                                 val sunset:String,
                                 val weather:List<Weather>,
                                 val humidity:Int,
                                 val pressure:Int
    )
data class Temp(val max:Double,
                val min:Double)

//data class CurrentDay(val timezone_offset: Long,
//                        val current:CurrentDayDetails)



