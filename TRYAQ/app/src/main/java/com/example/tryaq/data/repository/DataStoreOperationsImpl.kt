package com.example.tryaq.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.tryaq.domain.repository.DataStoreOperations
import com.example.tryaq.util.Constants.PATIENT_ID_KEY
import com.example.tryaq.util.Constants.PATIENT_IMAGE_KEY
import com.example.tryaq.util.Constants.PATIENT_NAME_KEY
import com.example.tryaq.util.Constants.PREFERENCES_KEY
import com.example.tryaq.util.Constants.PREFERENCES_NAME
import com.example.tryaq.util.Constants.REMEMBER_ME_KEY
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

    override suspend fun savePatientData(id: Int,name: String, image: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.patientIdKey] = id
            preferences[PreferencesKey.patientNameKey] = name
            preferences[PreferencesKey.patientImageKey] = image
        }
    }

    override fun readPatientId(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.patientIdKey] ?: -1
                savePatientData
            }
    }
    override fun readPatientName(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.patientNameKey] ?: ""
                savePatientData
            }
    }
    override fun readPatientImage(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val savePatientData = preferences[PreferencesKey.patientImageKey] ?: ""
                savePatientData
            }
    }
    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
        val rememberMeKey = booleanPreferencesKey(name = REMEMBER_ME_KEY)
        val patientIdKey = intPreferencesKey(name = PATIENT_ID_KEY)
        val patientNameKey = stringPreferencesKey(name = PATIENT_NAME_KEY)
        val patientImageKey = stringPreferencesKey(name = PATIENT_IMAGE_KEY)
    }
}