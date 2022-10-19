package com.example.dr_tryaq.data.paging_source

import androidx.paging.*
import androidx.room.withTransaction
import com.example.dr_tryaq.data.local.TryaqDatabase
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.BaseResponse
import javax.inject.Inject

@ExperimentalPagingApi
class CancelAppointmentRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val tryaqDatabase: TryaqDatabase,
    private val appointmentId: Int
) : PagingSource<Int, BaseResponse>() {

    private val dao = tryaqDatabase.tryaqDao()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseResponse> {
        return try {
            val response = tryaqApi.cancelAppointment(appointmentId = appointmentId)

            val baseResponse = listOf(response)

            if (response.success!!) {
                tryaqDatabase.withTransaction {
                    dao.deleteAppointment(appointmentId = appointmentId)
                }
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