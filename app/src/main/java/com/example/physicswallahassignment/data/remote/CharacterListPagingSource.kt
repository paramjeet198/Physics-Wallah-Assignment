package com.example.physicswallahassignment.data.remote

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.physicswallahassignment.data.remote.model.Character
import com.example.physicswallahassignment.domain.repository.CharacterRepository

class CharacterListPagingSource(
    private val repo: CharacterRepository
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repo.getCharacters(nextPageNumber)

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = getNextPageNumber(response.info?.next)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }


    private fun getNextPageNumber(nextUrl: String?): Int? {
        return nextUrl?.let {
            Uri.parse(it).getQueryParameter("page")?.toIntOrNull()
        }
    }

}