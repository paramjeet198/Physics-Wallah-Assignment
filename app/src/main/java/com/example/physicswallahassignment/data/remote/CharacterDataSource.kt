package com.example.physicswallahassignment.data.remote

import com.example.physicswallahassignment.data.remote.api.RickAndMortyApiService
import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse
import com.example.physicswallahassignment.data.remote.model.CharacterListResponse
import javax.inject.Inject

class CharacterDataSource @Inject constructor(private val apiService: RickAndMortyApiService) {

    suspend fun getCharacters(page: Int): CharacterListResponse {
        return apiService.getCharacters(page).body()!!
    }

    suspend fun getCharacterDetail(id: Int): CharacterDetailResponse? =
        apiService.getCharacterDetail(id).body()
}