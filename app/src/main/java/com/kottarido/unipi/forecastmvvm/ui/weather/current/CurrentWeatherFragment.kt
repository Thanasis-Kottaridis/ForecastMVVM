package com.kottarido.unipi.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kottarido.unipi.forecastmvvm.R
import com.kottarido.unipi.forecastmvvm.data.WeatherApiService
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel

        //TEST
        // Ftiaxnoume ena instance tou WeatherApiService gia na doume an douleuei
        // paralipontas ola ta vimata (viewModel,Repository klp) gia test purposes

        val apiService = WeatherApiService()

        GlobalScope.launch (Dispatchers.Main){
            val currentWeatherResponse = apiService.getCurrentWeather("london").await()
            textView.text = currentWeatherResponse.currentWeatherEntry.toString()
        }
    }

}
