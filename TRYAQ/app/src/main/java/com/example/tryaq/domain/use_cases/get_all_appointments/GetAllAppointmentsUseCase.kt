package com.example.tryaq.domain.use_cases.get_all_appointments

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Ad
import com.example.tryaq.domain.model.models.Appointment
import kotlinx.coroutines.flow.Flow

class GetAllAppointmentsUseCase (
    private val repository: Repository
) {
    operator fun invoke(patientId: Int): Flow<PagingData<Appointment>> {
        return repository.getAllAppointments(patientId = patientId)
    }
}