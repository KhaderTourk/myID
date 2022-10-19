package com.example.tryaq.presentation.screens.bottom_nav_screens.results

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.tryaq.domain.model.models.Diagnosis
import com.example.tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    fun getAllDiagnosis(patientId: Int): Flow<PagingData<Diagnosis>> {
        return  useCases.getAllDiagnosisUseCase(patientId = patientId)
    }


}