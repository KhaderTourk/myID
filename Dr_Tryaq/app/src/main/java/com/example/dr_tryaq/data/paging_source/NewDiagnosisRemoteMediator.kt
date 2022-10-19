package com.example.dr_tryaq.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.BaseResponse
import javax.inject.Inject

@ExperimentalPagingApi
class NewDiagnosisRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val doctorId: Int,
    private val patientId: Int,
    private val serviceId: Int,
    private val appointmentId: Int,
    private val serviceName: String,
    private val analysisRequired: String,
    private val medicineRequired: String,
    private val date: String,
    private val time: String,
    private val analyzesResult: String,
    private val isAnalysis: Int,
    private val note: String,
    private val patientName: String,
    private val patientImage: String,
    private val serviceImage: String,
    private val doctorName: String
) :  PagingSource<Int, BaseResponse>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseResponse> {
        return try {
            val response =
                tryaqApi.newDiagnosis(
                    doctorId = doctorId,
                    appointmentId = appointmentId,
                    date = date,
                    time = time,
                    analysisRequired = analysisRequired,
                    doctorName = doctorName,
                    medicineRequired = medicineRequired,
                    serviceName = serviceName,
                    patientName = patientName,
                    patientImage = patientImage,
                    patientId = patientId,
                    serviceId = serviceId,
                    serviceImage = serviceImage,
                    note = note,
                    analyzesResult = analyzesResult,
                    isAnalysis = isAnalysis
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