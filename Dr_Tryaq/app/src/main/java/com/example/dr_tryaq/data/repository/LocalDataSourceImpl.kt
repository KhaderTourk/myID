package com.example.dr_tryaq.data.repository

import androidx.paging.PagingSource
import com.example.dr_tryaq.data.local.TryaqDatabase
import com.example.dr_tryaq.domain.model.models.Diagnosis
import com.example.dr_tryaq.domain.repository.LocalDataSource

class LocalDataSourceImpl(tryaqDatabase: TryaqDatabase): LocalDataSource {

    private val tryaqDao = tryaqDatabase.tryaqDao()

    override suspend fun getSelectedDiagnosis(diagnosisId: Int): Diagnosis {
        return tryaqDao.getSelectedDiagnosis(id = diagnosisId)
    }
    override suspend fun getMyPatients(): PagingSource<Int, Diagnosis> {
        return tryaqDao.getMyPatients()
    }
}