package com.kottarido.unipi.forecastmvvm.data.db.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class StringListConverter {


        val gson = GsonBuilder().setPrettyPrinting().create()

        @TypeConverter
        fun fromStringList(value:List<String>):String{
            return gson.toJson(value)
        }
        @TypeConverter
        fun toStringList(value: String):List<String>{
            return gson.fromJson(value,object : TypeToken<List<String>>(){}.type)
        }
}