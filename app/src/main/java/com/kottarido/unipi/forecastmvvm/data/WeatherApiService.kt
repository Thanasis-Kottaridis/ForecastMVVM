package com.kottarido.unipi.forecastmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kottarido.unipi.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//auto to Interface tha xrisimopoiithei apo tin library tou Retrofit
// gia na fortonei ta data apo to API

// ftiaxnoume mia const val i opoia tha apothikeuei to key gia to API
const val API_KEY = "87a08ec5e4ffbb17478d11bf35e631c8"

// episis kratame kai to query url gia exoume kapiou idous reference otan ftiaxnoume auto to API service
//queryURL = http://api.weatherstack.com/current?access_key=87a08ec5e4ffbb17478d11bf35e631c8&query=New%20York&lang=en

interface WeatherApiService {

    // arxika ftiaxnoume mia nea function gia na pernoume ton current weather
    // i opoia tha apotelei ena GET request (Ara tha exei annotation @GET)
    // auti i get tha dieukrinizei mono oti prokite na paroume ton Current kero
    // mesa sto tmima ton parametron tis i methodos tha dexete ena tin poli gia tin opoia theloume ton kero
    // ws String to opio apoteli tin value
    @GET("current")
    fun getCurrentWeather(
        // edo tha diaxiristoume to komati url pou afora to query (query=New%20York)
        // opou to querry einai i parametros tis polis pou theloume ton kero
        @Query("query") location: String,
        // mpori na exei kanei specify tin monada stin opoia tha epistrafoun ta apotelesmata
        // kai default monada einai metric
        @Query("units") unitCode: String = "m"
    ): Deferred<CurrentWeatherResponse>

    // episis xriazomaste kapiou idous class pou tha kanei implement auto to interface kai tha epistrefei ta
    // data apo to API giauto tha ftia3oume ena companion Object ( to companion Object einai kati san static
    // method)
    companion object{
        // tin fun invoke gt i invoke mas epitrepei kalontas WeatherApiService() na kaloume tin crate function
        // an itan fun create() tha eprepe na kalesoume weathrApiService.create()
        operator fun invoke():WeatherApiService{
            // auto to kanoume gt theloume na pername to key sto request se kathe call pou ginete
            // kai i kaliteri practice gia auto einai meso interceptor

            // O interceptor einai ena interface pou parakolouuei ta request
            // opote ftiaxnoume mia anonymous class kai xrisimopioume lamda syntax gia to chain
            val requestInterceptor = Interceptor{chain ->

                // pernoume apo to chain to url meso tou request pou paakolouthite
                // stin sinexia ftiaxnoume enan builder gia na kanoume sto url add tin parameter key me value API_KEY
                // kai kanoume build ton new builder
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                // stin sinexia ftiaxnoume to request kai kaloume newBuilder sto request
                // gia na kanoume set to url pou kaname update parapano
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                // telos apo olo auto to lamba function theloume na epistrepsoume enan interceptor
                // o opoios exei kanei proceed to updated request apo ton chain
                return@Interceptor chain.proceed(request)
            }

            // ftiaxnoume ena obj tis okHttpClient class i opoia dexete ton interceptor kai parakolouthei kathe call
            // opote ftiaxnoume enan builder kai tou pername ton requestInterceptor
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            // telos theloume na epistrepsoume to WeatherApiService apo tin invoke
            // opote tha ftia3oume mia implementation tou WeatherApiService meso tou retrofit
            // opote ftiaxnoume enan retrofit builder ston opion pername ton okHttpClient
            //  orizoume oti to baseURL gia kathe call tha einai "http://api.weatherstack.com/" (to url tou API diladi)
            // epidi xrisimopoioume Deferred prepei na kanoume specify enan addCallAdapterFactory gia to retrofit
            // episis prosthetoume converterFactory gia na kanoume specify oti xrisimopoioume Gson
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)

        }
    }
}