package com.example.tryaq.domain.use_cases.get_image

import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Diagnosis

class GetSelectedDiagnosisUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(diagnosisId: Int): Diagnosis {
        return repository.getSelectedDiagnosis(diagnosisId = diagnosisId)
    }
}