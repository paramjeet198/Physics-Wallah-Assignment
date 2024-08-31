package com.example.physicswallahassignment.ui.screens.characterlist

import com.example.physicswallahassignment.data.remote.model.CharacterListResponse

data class CharacterStateHolder(
    val isLoading: Boolean = false,
    val data: CharacterListResponse? = null,
    val error: String? = null
)
