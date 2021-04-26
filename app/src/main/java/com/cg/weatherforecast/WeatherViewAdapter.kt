package com.cg.weatherforecast

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.cg.weatherforecast.dummy.DummyContent.DummyItem
import java.text.SimpleDateFormat
import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class WeatherViewAdapter(
    private val values: List<Properties>
) : RecyclerView.Adapter<WeatherViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.days_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        val wea=item.weather.get(0)
        val temp=item.temp
        val da= SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(item.dt*1000))
        holder.date.append(da)
        holder.discription.append(wea.description)
        holder.maxTemp.append("${temp.max} ℃ ")
        holder.minTemp.append("${temp.min} ℃")
        val imagepath=wea.icon
        val imageURL="https://openweathermap.org/img/wn/$imagepath.png"
        Glide.with(holder.itemView.context).load(Uri.parse(imageURL)).into(holder.posterIV)
       // holder.date.append(item.)
       // holder.contentView.text = item.content
    }

    override fun getItemCount()= values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date=view.findViewById<TextView>(R.id.dateT)
        val discription=view.findViewById<TextView>(R.id.description)
        val minTemp=view.findViewById<TextView>(R.id.minTemp)
        val maxTemp=view.findViewById<TextView>(R.id.maxTemp)
        val posterIV=view.findViewById<ImageView>(R.id.imageView)



    }
}