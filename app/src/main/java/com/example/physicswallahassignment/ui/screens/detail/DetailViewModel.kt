package com.example.physicswallahassignment.ui.screens.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physicswallahassignment.common.Resource
import com.example.physicswallahassignment.data.repository.CharacterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    private val _detailState = MutableStateFlow(DetailStateHolder())
    val detailStateHolder: StateFlow<DetailStateHolder> = _detailState

    init {
        Log.v("", "Character Detail ViewModel initialized")
        fetchCharacterDetail()
    }


    private fun fetchCharacterDetail() {
        viewModelScope.launch {
            _detailState.value = DetailStateHolder(isLoading = true)

            delay(1000)
            val id = savedStateHandle.get<Int>("id") ?: 1
            when (val response = repository.getCharacterDetail(id)) {
                is Resource.Error -> {
                    _detailState.value =
                        DetailStateHolder(error = response.error.toString())
                }

                is Resource.Success -> {
                    _detailState.value = DetailStateHolder(data = response.data)
                }

                else -> {

                }
            }
        }
    }


}