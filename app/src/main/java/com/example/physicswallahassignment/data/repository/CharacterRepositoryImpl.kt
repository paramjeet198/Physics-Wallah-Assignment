package com.example.physicswallahassignment.data.repository

import com.example.physicswallahassignment.common.Resource
import com.example.physicswallahassignment.data.remote.CharacterDataSource
import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse
import com.example.physicswallahassignment.data.remote.model.CharacterListResponse
import com.example.physicswallahassignment.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val dataSource: CharacterDataSource) :
    CharacterRepository {

    override suspend fun getCharacters(page: Int): CharacterListResponse =
        withContext(Dispatchers.IO) {
            try {
                delay(1000) // delay to show loading state
                dataSource.getCharacters(page)
            } catch (e: Exception) {
                throw e
            }
        }


    override suspend fun getCharacterDetail(id: Int): Resource<CharacterDetailResponse> =
        withContext(Dispatchers.IO) {
            try {
                val characters = dataSource.getCharacterDetail(id)
                Resource.Success(data = characters)
            } catch (e: Exception) {
                Resource.Error(e.message.toString())
            }
        }

}