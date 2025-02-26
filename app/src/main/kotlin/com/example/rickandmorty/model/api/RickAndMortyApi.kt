package com.example.rickandmorty.model.api

import com.example.rickandmorty.model.data.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(@Query("name") name: String): RickAndMortyResponse
}