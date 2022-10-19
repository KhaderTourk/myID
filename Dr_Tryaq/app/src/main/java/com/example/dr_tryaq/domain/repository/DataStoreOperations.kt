package com.example.dr_tryaq.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveRememberMeState(isChecked: Boolean)
    fun readRememberMeState(): Flow<Boolean>
    suspend fun saveDoctorData(id: Int,name: String, image: String)
    suspend fun saveDoctorDepartment(department: Int)
    fun readDoctorDepartment(): Flow<Int>
    fun readDoctorId(): Flow<Int>
    fun readDoctorName(): Flow<String>
    fun readDoctorImage(): Flow<String>
}