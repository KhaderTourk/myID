package com.example.dr_tryaq.domain.use_cases.get_all_diagnosis

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class NewDiagnosisUseCase (
    private val repository: Repository
) {
    operator fun invoke(
        doctorId: Int,
        patientId: Int,
        serviceId: Int,
        appointmentId: Int,
        serviceName: String,
        analysisRequired: String,
        medicineRequired: String,
        date: String,
        time: String,
        analyzesResult: String,
        isAnalysis: Int,
        note: String,
        patientName: String,
        patientImage: String,
        serviceImage: String,
        doctorName: String
    ): Flow<PagingData<BaseResponse>> {
        return repository.newDiagnosis(
            doctorId = doctorId,
            appointmentId = appointmentId,
            date = date,
            time = time,
            analysisRequired = analysisRequired,
            doctorName = doctorName,
            medicineRequired = medicineRequired,
            serviceName = serviceName,
            patientName = patientName,
            patientImage = patientImage,
            patientId = patientId,
            serviceId = serviceId,
            serviceImage = serviceImage,
            note = note,
            analyzesResult = analyzesResult,
            isAnalysis = isAnalysis
        )
    }
}