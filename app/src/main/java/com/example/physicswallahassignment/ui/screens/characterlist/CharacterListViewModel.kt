package com.example.physicswallahassignment.ui.screens.characterlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.physicswallahassignment.data.remote.CharacterListPagingSource
import com.example.physicswallahassignment.data.repository.CharacterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

//    private val _characterList = MutableStateFlow(CharacterStateHolder())
//    val characterList: StateFlow<CharacterStateHolder> = _characterList

    val characterPager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        CharacterListPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}