package com.example.paging3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retroinstance {
    companion object{
        val BASE_URL = "https://rickandmortyapi.com/api/"

        fun getRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}