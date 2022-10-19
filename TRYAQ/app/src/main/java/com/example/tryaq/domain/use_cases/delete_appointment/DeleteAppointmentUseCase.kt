package com.example.tryaq.domain.use_cases.delete_appointment

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Appointment
import com.example.tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class DeleteAppointmentUseCase (
    private val repository: Repository
) {
     operator fun invoke(appointmentId: Int): Flow<PagingData<BaseResponse>> {
        return repository.deleteAppointment(appointmentId = appointmentId)
    }
}