package com.example.dr_tryaq.domain.repository

import androidx.paging.PagingSource
import com.example.dr_tryaq.domain.model.models.Diagnosis


interface LocalDataSource {
    suspend fun getSelectedDiagnosis(diagnosisId: Int): Diagnosis
    suspend fun getMyPatients(): PagingSource<Int, Diagnosis>
}