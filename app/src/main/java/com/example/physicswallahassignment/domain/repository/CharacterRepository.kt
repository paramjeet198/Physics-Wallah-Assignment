package com.example.physicswallahassignment.domain.repository

import com.example.physicswallahassignment.common.Resource
import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse
import com.example.physicswallahassignment.data.remote.model.CharacterListResponse

interface CharacterRepository {

    suspend fun getCharacters(page: Int): CharacterListResponse
//    suspend fun getCharacters(page: Int): Resource<CharacterListResponse>

    suspend fun getCharacterDetail(id: Int): Resource<CharacterDetailResponse>
}