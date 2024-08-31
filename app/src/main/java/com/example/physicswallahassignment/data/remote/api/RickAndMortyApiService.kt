package com.example.physicswallahassignment.data.remote.api

import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse
import com.example.physicswallahassignment.data.remote.model.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickAndMortyApiService {

    @GET("/api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterListResponse>

    @GET("/api/character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Response<CharacterDetailResponse>

}
