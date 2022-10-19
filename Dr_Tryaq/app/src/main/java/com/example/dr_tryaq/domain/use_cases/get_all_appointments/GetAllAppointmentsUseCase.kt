package com.example.dr_tryaq.domain.use_cases.get_all_appointments

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Appointment
import kotlinx.coroutines.flow.Flow

class GetAllAppointmentsUseCase (
    private val repository: Repository
) {
    operator fun invoke(departmentId: Int): Flow<PagingData<Appointment>> {
        return repository.getAllAppointments(departmentId = departmentId)
    }
}