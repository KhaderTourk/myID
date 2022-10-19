package com.example.tryaq.domain.use_cases.get_all_appointments

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Appointment2
import kotlinx.coroutines.flow.Flow

class GetAllAppointmentsUseCase2 (
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Appointment2>> {
        return repository.getAllAppointments2()
    }
}