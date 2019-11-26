package com.kottarido.unipi.forecastmvvm.data

import com.google.gson.annotations.SerializedName

// otan xrisimopoioume Gson gia na metatrepsoume ena json se kotlin obj i vivliothiki (Gson) psaxnei
// gia ti class i to property pou exei ton idio tipo kai onoma stin class stin opoia theloume na
// metatrepsoume to json. Opote ama exoume valei onoma stin class H sto property apo auto pou exei to json
// xrisimopoioume to anotation @SerializedName("kai edo vazoume to name pou exei to property sto json")
data class CurrentWeatherResponse(
    val request: Request,
    val location: Location,
    // kanoume serialize to currentWeatherEntry na anaferete sto current property tou json
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)