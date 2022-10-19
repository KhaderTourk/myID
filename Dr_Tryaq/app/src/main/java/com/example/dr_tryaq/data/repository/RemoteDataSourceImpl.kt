package com.example.dr_tryaq.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dr_tryaq.data.local.TryaqDatabase
import com.example.dr_tryaq.data.paging_source.*
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.*
import com.example.dr_tryaq.domain.repository.RemoteDataSource
import com.example.dr_tryaq.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val tryaqApi: TryaqApi,
    private val tryaqDatabase: TryaqDatabase
) : RemoteDataSource {

    private val tryaqDao = tryaqDatabase.tryaqDao()

    override fun getAllAds(): Flow<PagingData<Ad>> {
        val pagingSourceFactory = { tryaqDao.getAllAds() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = AdRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getAllAppointments(departmentId: Int): Flow<PagingData<Appointment>> {
        val pagingSourceFactory = { tryaqDao.getAllAppointments() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = AppointmentRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase,
                departmentId = departmentId
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun addAppointment(
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
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                AddAppointmentRemoteMediator(
                    tryaqApi = tryaqApi,
                    id = id,
                    day = day,
                    date = date,
                    time = time,
                    month = month,
                    doctorName=doctorName,
                    doctorImage=doctorImage,
                    serviceName=serviceName,
                    patientId=patientId,
                    serviceId=serviceId,
                    patientName = patientName,
                    patientImage = patientImage,
                    departmentId=departmentId
                )
            }

        ).flow
    }

    override fun newDiagnosis(
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
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                NewDiagnosisRemoteMediator(
                    tryaqApi = tryaqApi,
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

        ).flow
    }

    override fun newService(
        departmentId: Int,
        name: String,
        image: String,
        price: String,
    ): Flow<PagingData<BaseResponse>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                NewServiceRemoteMediator(
                tryaqApi = tryaqApi,
                    departmentId = departmentId,
                    name = name,
                    image = image,
                    price = price
            )
            }

        ).flow
    }

    override fun changeServiceState(serviceId: Int): Flow<PagingData<BaseResponse>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                ChangeServiceStateRemoteMediator(
                    tryaqApi = tryaqApi,
                    serviceId = serviceId
                )
            }
        ).flow
    }

    override fun deleteAppointment(appointmentId: Int): Flow<PagingData<BaseResponse>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                CancelAppointmentRemoteMediator(
                    tryaqApi = tryaqApi,
                    tryaqDatabase = tryaqDatabase,
                    appointmentId = appointmentId
                )
            }
        ).flow
    }

    override fun getAllDepartments(): Flow<PagingData<Department>> {
        val pagingSourceFactory = { tryaqDao.getAllDepartments() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = DepartmentRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getAllDiagnosis(doctorId: Int): Flow<PagingData<Diagnosis>> {
        val pagingSourceFactory = { tryaqDao.getAllDiagnosis() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = DiagnosisRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase,
                doctorId = doctorId
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getAllMedicines(): Flow<PagingData<Medicine>> {
        val pagingSourceFactory = { tryaqDao.getAllMedicines() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = MedicineRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchMedicines(query: String): Flow<PagingData<Medicine>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchMedicinesSource(tryaqApi = tryaqApi, query = query)
            }
        ).flow
    }

    override fun login(doctorId: Int, password: String): Flow<PagingData<Doctor>> {
        val pagingSourceFactory = { tryaqDao.getAllDoctors() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = DoctorRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase,
                doctorId = doctorId,
                password = password
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getDepartmentServices(departmentId: Int): Flow<PagingData<Service>> {
        val pagingSourceFactory = { tryaqDao.getAllServices() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = ServiceRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase,
                departmentId = departmentId
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchAds(): Flow<PagingData<Ad>> {
        TODO("Not yet implemented")
    }
}