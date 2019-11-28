package com.kottarido.unipi.forecastmvvm.data.network.response


data class Request(
    val type: String,
    val query: String,
    val language: String,
    val unit: String
)