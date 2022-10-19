package com.example.dr_tryaq.domain.use_cases.get_image

import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Diagnosis


class GetSelectedDiagnosisUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(diagnosisId: Int): Diagnosis {
        return repository.getSelectedDiagnosis(diagnosisId = diagnosisId)
    }
}