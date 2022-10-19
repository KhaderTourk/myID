package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.new_diagnosis

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.dr_tryaq.domain.model.models.BaseResponse
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewDiagnosisViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    val analysis = useCases.getAllServicesUseCase(departmentId = 5)
    val medicines = useCases.getAllMedicinesUseCase()

    fun newDiagnosis(
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
        return useCases.newDiagnosisUseCase(
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