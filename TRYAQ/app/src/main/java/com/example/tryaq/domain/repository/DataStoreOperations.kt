package com.example.tryaq.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveRememberMeState(isChecked: Boolean)
    fun readRememberMeState(): Flow<Boolean>
    suspend fun savePatientData(id: Int,name: String, image: String)
    fun readPatientId(): Flow<Int>
    fun readPatientName(): Flow<String>
    fun readPatientImage(): Flow<String>
}