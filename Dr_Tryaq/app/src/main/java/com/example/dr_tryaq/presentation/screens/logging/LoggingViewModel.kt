package com.example.dr_tryaq.presentation.screens.logging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.dr_tryaq.domain.model.models.Doctor
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggingViewModel @Inject constructor(
   private val useCases: UseCases
): ViewModel() {



    fun saveDoctorData(id: Int, name:String, image:String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveDoctorDataUseCase(id = id, name = name, image = image)
        }
    }
    fun saveDoctorDepartment(department: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveDoctorDepartmentUseCase(department = department)
        }
    }

    fun saveRememberState(isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveRememberMeUseCase(isChecked = isChecked)
        }
    }

    fun login(doctorId: Int, password: String): Flow<PagingData<Doctor>> {
        return useCases.login(doctorId = doctorId, password = password)
    }


}