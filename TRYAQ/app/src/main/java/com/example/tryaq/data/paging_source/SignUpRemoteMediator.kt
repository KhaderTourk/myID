package com.example.tryaq.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tryaq.data.remote.TryaqApi
import com.example.tryaq.domain.model.models.BaseResponse
import javax.inject.Inject

@ExperimentalPagingApi
class SignUpRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val id: Int,
    private val password: String,
) :  PagingSource<Int, BaseResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseResponse> {
        return try {
            val response = tryaqApi.signUp(patientId = id, password = password)

            val baseResponse = listOf(response)
            Log.e("message :",response.message!!)
            Log.e("message :",response.success.toString())

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