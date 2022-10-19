package com.example.dr_tryaq.domain.use_cases.get_all_diagnosis

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Diagnosis
import kotlinx.coroutines.flow.Flow

class GetAllDiagnosisUseCase(
    private val repository: Repository
) {
    operator fun invoke(doctorId: Int): Flow<PagingData<Diagnosis>> {
        return repository.getAllDiagnosis(doctorId = doctorId)
    }
}