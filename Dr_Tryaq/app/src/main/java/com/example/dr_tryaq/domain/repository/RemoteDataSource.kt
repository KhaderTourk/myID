package com.example.dr_tryaq.domain.repository

import androidx.paging.PagingData
import com.example.dr_tryaq.domain.model.models.*
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllAds(): Flow<PagingData<Ad>>
    fun getAllAppointments(departmentId: Int): Flow<PagingData<Appointment>>
    fun deleteAppointment(appointmentId: Int): Flow<PagingData<BaseResponse>>
    fun changeServiceState(serviceId: Int): Flow<PagingData<BaseResponse>>
    fun newService(
        departmentId: Int,
        name: String,
        image: String,
        price: String,
    ): Flow<PagingData<BaseResponse>>

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
    ): Flow<PagingData<BaseResponse>>

    fun getAllDepartments(): Flow<PagingData<Department>>
    fun getAllDiagnosis(doctorId: Int): Flow<PagingData<Diagnosis>>
    fun getAllMedicines(): Flow<PagingData<Medicine>>
    fun searchMedicines(query: String): Flow<PagingData<Medicine>>
    fun login(doctorId: Int, password: String): Flow<PagingData<Doctor>>
    fun getDepartmentServices(departmentId: Int): Flow<PagingData<Service>>
    fun searchAds(): Flow<PagingData<Ad>>
}
