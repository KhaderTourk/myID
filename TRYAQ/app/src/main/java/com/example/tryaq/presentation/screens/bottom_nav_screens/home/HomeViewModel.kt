package com.example.tryaq.presentation.screens.bottom_nav_screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.tryaq.domain.model.models.Appointment
import com.example.tryaq.domain.model.models.BaseResponse
import com.example.tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    val getAllAds = useCases.getAllAdsUseCase()
    val getAllDepartments = useCases.getAllDepartmentsUseCase()

    fun getAllAppointments(patientId: Int): Flow<PagingData<Appointment>> {
        return useCases.getAllAppointmentsUseCase(patientId = patientId)
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
    fun signUp(
        patientId: Int,
        password: String
    ): Flow<PagingData<BaseResponse>> {
        return useCases.signUpUseCase(
            patientId = patientId,
            password = password
        )
    }
}
