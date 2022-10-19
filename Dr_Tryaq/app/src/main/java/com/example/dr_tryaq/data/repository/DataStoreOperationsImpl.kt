package com.example.dr_tryaq.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.dr_tryaq.domain.repository.DataStoreOperations
import com.example.dr_tryaq.util.Constants.DOCTOR_DEPARTMENT_KEY
import com.example.dr_tryaq.util.Constants.DOCTOR_ID_KEY
import com.example.dr_tryaq.util.Constants.DOCTOR_IMAGE_KEY
import com.example.dr_tryaq.util.Constants.DOCTOR_NAME_KEY
import com.example.dr_tryaq.util.Constants.PREFERENCES_KEY
import com.example.dr_tryaq.util.Constants.PREFERENCES_NAME
import com.example.dr_tryaq.util.Constants.REMEMBER_ME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {
    private val dataStore = context.dataStore
    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }
    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveRememberMeState(isChecked: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.rememberMeKey] = isChecked
        }
    }

    override fun readRememberMeState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val rememberMeState = preferences[PreferencesKey.rememberMeKey] ?: false
                rememberMeState
            }
    }

    override suspend fun saveDoctorData(id: Int,name: String, image: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.doctorIdKey] = id
            preferences[PreferencesKey.doctorNameKey] = name
            preferences[PreferencesKey.doctorImageKey] = image
        }
    }

    override suspend fun saveDoctorDepartment(department: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.doctorDepartmentKey] = department
        }
    }

    override fun readDoctorDepartment(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.doctorDepartmentKey] ?: -1
                savePatientData
            }
    }

    override fun readDoctorId(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.doctorIdKey] ?: -1
                savePatientData
            }
    }
    override fun readDoctorName(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.doctorNameKey] ?: ""
                savePatientData
            }
    }
    override fun readDoctorImage(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.doctorImageKey] ?: ""
                savePatientData
            }
    }
    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
        val rememberMeKey = booleanPreferencesKey(name = REMEMBER_ME_KEY)
        val doctorIdKey = intPreferencesKey(name = DOCTOR_ID_KEY)
        val doctorDepartmentKey = intPreferencesKey(name = DOCTOR_DEPARTMENT_KEY)
        val doctorNameKey = stringPreferencesKey(name = DOCTOR_NAME_KEY)
        val doctorImageKey = stringPreferencesKey(name = DOCTOR_IMAGE_KEY)
    }
}