package com.example.physicswallahassignment.ui.screens.detail

import com.example.physicswallahassignment.data.remote.model.CharacterDetailResponse

data class DetailStateHolder(
    val isLoading: Boolean = false,
    val data: CharacterDetailResponse? = null,
    val error: String? = null
)
