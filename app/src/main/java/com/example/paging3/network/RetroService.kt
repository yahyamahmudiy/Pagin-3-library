package com.example.paging3.network

import com.example.paging3.model.RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("character")
    suspend fun getDataFromApi(@Query("page") query: Int): RickAndMortyList
}