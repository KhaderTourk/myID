package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.dr_tryaq.domain.model.models.Diagnosis
import com.example.dr_tryaq.domain.use_cases.UseCases
import com.example.dr_tryaq.util.Constants.MY_PATIENTS_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    fun getAllDiagnosis(doctorId: Int): Flow<PagingData<Diagnosis>> {
        return useCases.getAllDiagnosisUseCase(doctorId = doctorId)
    }

    private val _myPatients: MutableStateFlow<PagingSource<Int, Diagnosis>?> = MutableStateFlow(null)
    val myPatients: StateFlow<PagingSource<Int, Diagnosis>?> = _myPatients

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val doctorId = savedStateHandle.get<Int>(MY_PATIENTS_KEY)
            _myPatients.value = doctorId?.let { useCases.getMyPatientsUseCase() }
        }
    }
}