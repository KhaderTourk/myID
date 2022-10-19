package com.example.tryaq.domain.repository

import androidx.paging.PagingData
import com.example.tryaq.domain.model.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface RemoteDataSource {
    fun getAllAds(): Flow<PagingData<Ad>>
    fun getAllAppointments(patientId: Int): Flow<PagingData<Appointment>>
    fun getAllAppointments2(): Flow<PagingData<Appointment2>>
     fun deleteAppointment(appointmentId: Int): Flow<PagingData<BaseResponse>>
     fun signUp( patientId: Int, password: String): Flow<PagingData<BaseResponse>>
     fun addAppointment(
         id: Int,
         day: String,
         date: String,
         time: String,
         month: String,
         doctorName: String,
         doctorImage: String,
         serviceName: String,
         patientName: String,
         patientImage: String,
         patientId: Int,
         serviceId: Int,
         departmentId: Int
     ): Flow<PagingData<BaseResponse>>
    fun getAllDepartments(): Flow<PagingData<Department>>
    fun getAllDiagnosis(patientId: Int): Flow<PagingData<Diagnosis>>
    fun getAllMedicines(): Flow<PagingData<Medicine>>
    fun searchMedicines(query: String): Flow<PagingData<Medicine>>
    fun getPatient(patientId: Int, password: String): Flow<PagingData<Patient>>
    fun getDepartmentServices(departmentId: Int): Flow<PagingData<Service>>
    fun searchAds(): Flow<PagingData<Ad>>
}
