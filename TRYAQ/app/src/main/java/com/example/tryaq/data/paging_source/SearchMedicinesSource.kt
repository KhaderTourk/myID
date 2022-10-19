package com.example.tryaq.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tryaq.data.remote.TryaqApi
import com.example.tryaq.domain.model.models.Medicine
import javax.inject.Inject

class SearchMedicinesSource @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val query: String
) : PagingSource<Int, Medicine>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Medicine> {
        return try {
            val apiResponse = tryaqApi.searchMedicines(name = query)
            val medicines = apiResponse.medicines
            if (medicines.isNotEmpty()) {
                LoadResult.Page(
                    data = medicines,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Medicine>): Int? {
        return state.anchorPosition
    }
}