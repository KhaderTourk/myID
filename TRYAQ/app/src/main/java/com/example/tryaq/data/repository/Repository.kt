package com.example.tryaq.data.repository

import androidx.paging.PagingData
import com.example.tryaq.domain.model.models.*
import com.example.tryaq.domain.repository.DataStoreOperations
import com.example.tryaq.domain.repository.LocalDataSource
import com.example.tryaq.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {
    fun getAllAds(): Flow<PagingData<Ad>> {
        return remote.getAllAds()
    }
    fun getAllAppointments(patientId: Int): Flow<PagingData<Appointment>> {
        return remote.getAllAppointments(patientId = patientId)
    }
    fun getAllAppointments2(): Flow<PagingData<Appointment2>> {
        return remote.getAllAppointments2()
    }
    fun getAllDepartments(): Flow<PagingData<Department>> {
        return remote.getAllDepartments()
    }
    fun getAllDiagnosis(patientId: Int): Flow<PagingData<Diagnosis>> {
        return remote.getAllDiagnosis(patientId = patientId)
    }
    fun getAllMedicines(): Flow<PagingData<Medicine>> {
        return remote.getAllMedicines()
    }
    fun searchMedicines(query: String): Flow<PagingData<Medicine>> {
        return remote.searchMedicines(query = query)
    }
    fun getPatient(patientId: Int, password: String): Flow<PagingData<Patient>> {
        return remote.getPatient(patientId = patientId, password = password)
    }
    fun getDepartmentServices(departmentId: Int): Flow<PagingData<Service>> {
        return remote.getDepartmentServices(departmentId = departmentId)
    }

    suspend fun getSelectedDiagnosis(diagnosisId: Int): Diagnosis {
        return local.getSelectedDiagnosis(diagnosisId = diagnosisId)
    }
     fun deleteAppointment(appointmentId: Int): Flow<PagingData<BaseResponse>> {
        return remote.deleteAppointment(appointmentId = appointmentId)
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
         patientName: String,
         patientImage: String,
         patientId: Int,
         serviceId: Int,
         departmentId: Int
     ): Flow<PagingData<BaseResponse>> {
        return remote.addAppointment(
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
        return remote.signUp(
            patientId=patientId,
            password = password
        )
    }
    fun addAppointment(patientId: Int,
                       password: String): Flow<PagingData<BaseResponse>> {
        return remote.signUp(
            patientId=patientId,
            password = password
        )
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
    suspend fun saveRememberMeState(isChecked: Boolean) {
        dataStore.saveRememberMeState(isChecked = isChecked)
    }

    fun readRememberMeState(): Flow<Boolean> {
        return dataStore.readRememberMeState()
    }
    suspend fun savePatientData(id: Int,name: String, image: String) {
        dataStore.savePatientData(id = id, name = name, image = image)
    }

    fun readPatientId(): Flow<Int> {
        return dataStore.readPatientId()
    }
    fun readPatientName(): Flow<String> {
        return dataStore.readPatientName()
    }
    fun readPatientImage(): Flow<String> {
        return dataStore.readPatientImage()
    }

}