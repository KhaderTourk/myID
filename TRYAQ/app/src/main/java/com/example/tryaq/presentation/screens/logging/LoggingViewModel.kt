package com.example.tryaq.presentation.screens.logging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.tryaq.domain.model.models.Patient
import com.example.tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggingViewModel @Inject constructor(
   private val useCases: UseCases
): ViewModel() {



    fun savePatientData(id: Int,name:String, image:String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.savePatientDataUseCase(id = id, name = name, image = image)
        }
    }

    fun saveRememberState(isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveRememberMeUseCase(isChecked = isChecked)
        }
    }

    fun getLoginResult(patientId: Int, password: String): Flow<PagingData<Patient>> {
        return useCases.getPatientUseCase(patientId = patientId, password = password)
    }


}