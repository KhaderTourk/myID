package com.example.tryaq.domain.repository

import com.example.tryaq.domain.model.models.Diagnosis

interface LocalDataSource {
    suspend fun getSelectedDiagnosis(diagnosisId: Int): Diagnosis
}