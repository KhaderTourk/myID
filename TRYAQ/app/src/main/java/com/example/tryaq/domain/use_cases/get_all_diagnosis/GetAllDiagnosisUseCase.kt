package com.example.tryaq.domain.use_cases.get_all_diagnosis

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Diagnosis
import kotlinx.coroutines.flow.Flow

class GetAllDiagnosisUseCase(
    private val repository: Repository
) {
    operator fun invoke(patientId: Int): Flow<PagingData<Diagnosis>> {
        return repository.getAllDiagnosis(patientId = patientId)
    }
}