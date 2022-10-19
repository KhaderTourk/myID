package com.example.dr_tryaq.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.dr_tryaq.domain.model.models.*
import com.example.dr_tryaq.domain.repository.DataStoreOperations
import com.example.dr_tryaq.domain.repository.LocalDataSource
import com.example.dr_tryaq.domain.repository.RemoteDataSource
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
    fun getAllAppointments(departmentId: Int): Flow<PagingData<Appointment>> {
        return remote.getAllAppointments(departmentId = departmentId)
    }
    fun getAllDepartments(): Flow<PagingData<Department>> {
        return remote.getAllDepartments()
    }
    fun getAllDiagnosis(doctorId: Int): Flow<PagingData<Diagnosis>> {
        return remote.getAllDiagnosis(doctorId = doctorId)
    }
    fun getAllMedicines(): Flow<PagingData<Medicine>> {
        return remote.getAllMedicines()
    }
    fun searchMedicines(query: String): Flow<PagingData<Medicine>> {
        return remote.searchMedicines(query = query)
    }
    fun login(doctorId: Int, password: String): Flow<PagingData<Doctor>> {
        return remote.login(doctorId = doctorId, password = password)
    }
    fun getDepartmentServices(departmentId: Int): Flow<PagingData<Service>> {
        return remote.getDepartmentServices(departmentId = departmentId)
    }

    suspend fun getSelectedDiagnosis(diagnosisId: Int): Diagnosis {
        return local.getSelectedDiagnosis(diagnosisId = diagnosisId)
    }

    suspend fun getMyPatients(): PagingSource<Int, Diagnosis> {
        return local.getMyPatients()
    }
     fun deleteAppointment(appointmentId: Int): Flow<PagingData<BaseResponse>> {
        return remote.deleteAppointment(appointmentId = appointmentId)
    }
     fun changeServiceState(serviceId: Int): Flow<PagingData<BaseResponse>> {
        return remote.changeServiceState(serviceId = serviceId)
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
        return remote.newDiagnosis(
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
     fun newService(
         departmentId: Int,
         name: String,
         image: String,
         price: String,
     ): Flow<PagingData<BaseResponse>> {
        return remote.newService(
            departmentId = departmentId,
            name = name,
            image = image,
            price = price
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
    suspend fun saveDoctorData(id: Int,name: String, image: String) {
        dataStore.saveDoctorData(id = id, name = name, image = image)
    }

    suspend fun saveDoctorDepartment(department: Int) {
        dataStore.saveDoctorDepartment(department = department)
    }

    fun readDoctorDepartment(): Flow<Int> {
        return dataStore.readDoctorDepartment()
    }

    fun readDoctorId(): Flow<Int> {
        return dataStore.readDoctorId()
    }
    fun readDoctorName(): Flow<String> {
        return dataStore.readDoctorName()
    }
    fun readDoctorImage(): Flow<String> {
        return dataStore.readDoctorImage()
    }

}