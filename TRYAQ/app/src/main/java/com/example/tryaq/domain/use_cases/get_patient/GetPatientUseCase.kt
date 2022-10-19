package com.example.tryaq.domain.use_cases.get_patient

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Patient
import kotlinx.coroutines.flow.Flow

class GetPatientUseCase(
    private val repository: Repository
) {
    operator fun invoke(patientId: Int, password: String): Flow<PagingData<Patient>> {
        return repository.getPatient(patientId = patientId, password = password)
    }
}