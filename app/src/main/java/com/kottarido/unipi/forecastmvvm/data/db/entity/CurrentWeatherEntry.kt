package com.kottarido.unipi.forecastmvvm.data.db.entity


import androidx.room.*
import com.google.gson.annotations.SerializedName

// pera apo to na travaei to current komati apo to json tou API se auti tin class
// dimiourgoume kai ena entity

// afou einai entity xriazete ena primary key
// mesa ston table tha exoume mono enan current weather
// opote tha to canoume constant auto to id
const val CURRENT_WEATHER_ID =0

// kanoume annotate auti tin class me to entity annotation tis androidX room library kai tou pernaw to table name
// kai xrisimopoioume ton TypeConverter gia na apothikeusoume tin List<String> stin Room ws json
// kai to antistrofo otan kanoume anagnosi
@Entity(tableName = "current_weather")
@TypeConverters(StringListConverter::class)
data class CurrentWeatherEntry(
    @SerializedName("observation_time")
    val observationTime: String,
    val temperature: Double,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    val precip: Double,
    val feelslike: Double,
    @SerializedName("uv_index")
    val uvIndex: Int,
    val visibility: Double
){
    // den to kanoume autoGenerate false gt den theloume tin ROOM na mas ftiaxnei id afou exoume mono 1
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}