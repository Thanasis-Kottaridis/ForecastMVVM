package com.kottarido.unipi.forecastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kottarido.unipi.forecastmvvm.data.db.entity.CurrentWeatherEntry

// class tis database tha einai abstract kai tin kanoume annotate ws Database
// tis opoias ta entities fia arxi tha einai ena array pou tha periexei mono i CurrentWeatherEntity::class
// kai i version tis einai gia arxi 1
// episis kanei inherit tin Room kai mas dimiourgei tin implementation tou CurrentWeatherDao Interface
@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
abstract class ForcastDatabase : RoomDatabase()  {
    // i current weatherDao function dimiourgei mia implementation tou CurrentWeatherDao interface kai to epistrefei
    abstract fun currentWeatherDao(): CurrentWeatherDao

    // episis ftiaxnoume ena companion object epidi theloume i database mas na einai singleton
    // episis tin proti fora pou tha ftia3oume ena instance tis forecast Database theloume na
    // kanoume build tin database me ena spacial method call tis room library
    companion object{
        // arxikopoioume tin timi tou instance tis ForecastDatabase me null
        // episis kanoume auto to property volatile diladi ola ta threads exoun immediate access se auto
        @Volatile private var instance : ForcastDatabase? = null

        // ftiaxnoume ena lock object epidi tha xrisimopoiisoume threads to opoio einai type Any(dil otidipote)
        // einai ena dummy object pou to xrisimopoioume apla gia na imaste sigouroi oti den tha kanoun 2 threads to idio pragma
        private val LOCK = Any()

        // ftiaxnoume tin operator fun invoke i opoia elenxei an to instance einai null an oxi to epistrefei opos einai
        // allios  kaloume ena synchronized block of code (to "?:" einai elenxos an einai != null)
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            // elenxoume 3ana edo an einai iso me null kai an oxi to epistrefoume
            // allios to dimiourgoume kalontas tin build database kai episis kanoume to instance iso me oti epistrefete apo auti (it)
            instance ?: buildDatabase(context).also { instance = it }

        }

        // auti i fun pernei ton database builder apo tin Room class kai tou pernaei to Context tis efarmogis
        // episis pername tin class tis database mas
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ForcastDatabase::class.java, "forecast.db")
                .build()

    }
}