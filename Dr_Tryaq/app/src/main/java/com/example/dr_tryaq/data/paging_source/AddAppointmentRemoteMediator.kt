package com.example.dr_tryaq.data.paging_source

import androidx.paging.*
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.BaseResponse
import javax.inject.Inject

@ExperimentalPagingApi
class AddAppointmentRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val id: Int,
    private val day: String,
    private val date: String,
    private val time: String,
    private val month: String,
    private val doctorName: String,
    private val doctorImage: String,
    private val serviceName: String,
    private val patientName: String,
    private val patientImage: String,
    private val patientId: Int,
    private val serviceId: Int,
    private val departmentId: Int,
) :  PagingSource<Int, BaseResponse>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseResponse> {
        return try {
            val response =
                tryaqApi.newAppointment(
                    id = id,
                    day = day,
                    date = date,
                    time = time,
                    month = month,
                    doctorName = doctorName,
                    doctorImage = doctorImage,
                    serviceName = serviceName,
                    patientName = patientName,
                    patientImage = patientImage,
                    patientId = patientId,
                    serviceId = serviceId,
                    departmentId = departmentId
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