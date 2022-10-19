package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.dr_tryaq.domain.model.models.Appointment
import com.example.dr_tryaq.domain.model.models.BaseResponse
import com.example.dr_tryaq.domain.model.models.Diagnosis
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    val getAllAds = useCases.getAllAdsUseCase()
    val getAllDepartments = useCases.getAllDepartmentsUseCase()

    fun getAllDiagnosis(doctorId: Int): Flow<PagingData<Diagnosis>> {
        return useCases.getAllDiagnosisUseCase(doctorId = doctorId)
    }
    fun getAllAppointments(departmentId: Int): Flow<PagingData<Appointment>> {
        return useCases.getAllAppointmentsUseCase(departmentId = departmentId)
    }
     fun deleteAppointment(appointmentId: Int): Flow<PagingData<BaseResponse>> {
        return useCases.deleteAppointmentUseCase(appointmentId = appointmentId)
    }
     fun addAppointment(
         id: Int,
         day: String,
         date: String,
         time: String,
         month: String,
         doctorName: String,
         doctorImage: String,
         serviceName: String,
         patientId: Int,
         patientName: String,
         patientImage: String,
         serviceId: Int,
         departmentId: Int
     ): Flow<PagingData<BaseResponse>> {
        return useCases.addAppointmentUseCase(
            id = id,
            day = day,
            date = date,
            time = time,
            month = month,
            doctorName=doctorName,
            doctorImage=doctorImage,
            serviceName=serviceName,
            patientName = patientName,
            patientImage = patientImage,
            patientId=patientId,
            serviceId=serviceId,
            departmentId=departmentId
        )
    }
}
