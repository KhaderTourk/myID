package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.results.result_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dr_tryaq.domain.model.models.Diagnosis
import com.example.dr_tryaq.domain.use_cases.UseCases
import com.example.dr_tryaq.util.Constants.DETAILS_DIAGNOSIS_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResultDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedDiagnosis: MutableStateFlow<Diagnosis?> = MutableStateFlow(null)
    val selectedDiagnosis: StateFlow<Diagnosis?> = _selectedDiagnosis

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val diagnosisId = savedStateHandle.get<Int>(DETAILS_DIAGNOSIS_KEY)
            _selectedDiagnosis.value = diagnosisId?.let { useCases.getSelectedDiagnosisUseCase(diagnosisId = diagnosisId) }
        }
    }
}