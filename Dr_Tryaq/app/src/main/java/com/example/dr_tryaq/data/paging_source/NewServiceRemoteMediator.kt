package com.example.dr_tryaq.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.BaseResponse
import javax.inject.Inject

@ExperimentalPagingApi
class NewServiceRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val departmentId: Int,
    private val name: String,
    private val image: String,
    private val price: String,
) :  PagingSource<Int, BaseResponse>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseResponse> {
        return try {
            val response =
                tryaqApi.newService(
                    departmentId = departmentId,
                    name = name,
                    image = image,
                    price = price
                )
            val baseResponse = listOf(response)

            if (response.success!!) {
                LoadResult.Page(
                    data = baseResponse,
                    prevKey = null,
                    nextKey = null
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

    override fun getRefreshKey(state: PagingState<Int, BaseResponse>): Int? {
        return state.anchorPosition
    }

}