package com.example.tryaq.data.repository

import com.example.tryaq.data.local.TryaqDatabase
import com.example.tryaq.domain.model.models.Diagnosis
import com.example.tryaq.domain.repository.LocalDataSource

class LocalDataSourceImpl(tryaqDatabase: TryaqDatabase): LocalDataSource {

    private val tryaqDao = tryaqDatabase.tryaqDao()

    override suspend fun getSelectedDiagnosis(diagnosisId: Int): Diagnosis {
        return tryaqDao.getSelectedDiagnosis(id = diagnosisId)
    }
}