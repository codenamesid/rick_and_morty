package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.api.RickAndMortyApi
import com.example.rickandmorty.model.data.RickAndMortyResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

class CharacterRepository(private val api: RickAndMortyApi) {
    suspend fun getCharacters(name: String): Result<RickAndMortyResponse> {
        return try {
            val response = api.getCharacters(name)
            Result.Success(response)
        } catch (unknownHostException: UnknownHostException) {
            Result.Error("No internet connection")
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = when (e) {
                is HttpException -> {
                    val errorBody = e.response()?.errorBody()?.string()
                    parseErrorMessage(errorBody)
                }
                else -> "Unknown error"
            }
            Result.Error("Error fetching characters: $errorMessage")
        }
    }

    private fun parseErrorMessage(message: String?): String {
        return try {
            val json = Gson().fromJson(message, JsonObject::class.java)
            json.get("error").asString
        } catch (e: Exception) {
            "Unknown error"
        }
    }
}