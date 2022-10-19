package com.example.dr_tryaq.domain.use_cases.delete_appointment

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class DeleteAppointmentUseCase (
    private val repository: Repository
) {
     operator fun invoke(appointmentId: Int): Flow<PagingData<BaseResponse>> {
        return repository.deleteAppointment(appointmentId = appointmentId)
    }
}