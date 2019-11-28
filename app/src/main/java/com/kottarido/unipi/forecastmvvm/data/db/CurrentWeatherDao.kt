package com.kottarido.unipi.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kottarido.unipi.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.kottarido.unipi.forecastmvvm.data.db.entity.CurrentWeatherEntry

// afou einai data access object to kanoume annotate me DAO (to opoio einai annotation tis room)
@Dao
interface CurrentWeatherDao {

    // function gia na kanoume add stin database (upsert = UPdate or inSERT tin idia stigmi)
    // auto gt an exoume egrafi stin db tha tin enimeronoume afou tha exoume mono mia egkrafi stin db
    // kanoume annotate tin upsert kai orizoume oti otan iparxei conflict sta insert tha kanei replace tin
    // eggrafi stin SQLite (conflict tha ginete sinexia gt panta tha kanoume insert stin thesi 0 tis db)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    // methodos gia na peernei ta data apo tin SQLite kai na epistrefei LiveData<CurrentWeatherEntry>
    // kai kanoume query na epistrepsi ta panta apo ton pinaka curent_weather opou to id einai 0
    @Query("select* from current_weather where id = $CURRENT_WEATHER_ID")
    fun getCurrentWeatherEntry():LiveData<CurrentWeatherEntry>
}