package com.example.dr_tryaq.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    private val _rememberMeChecked = MutableStateFlow(false)
    val isRememberChecked: StateFlow<Boolean> = _rememberMeChecked

    private val _doctorId = MutableStateFlow(-1)
    val doctorId: StateFlow<Int> = _doctorId

    private val _departmentId = MutableStateFlow(-1)
    val departmentId: StateFlow<Int> = _departmentId

    private val _doctorName = MutableStateFlow("")
    val doctorName: StateFlow<String> = _doctorName

    private val _doctorImage = MutableStateFlow("")
    val doctorImage: StateFlow<String> = _doctorImage

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value

            _rememberMeChecked.value =
            useCases.readRememberMeUseCase().stateIn(viewModelScope).value

            _departmentId.value =
                useCases.readDoctorDepartmentUseCase().stateIn(viewModelScope).value

            _doctorId.value =
                useCases.readDoctorIdUseCase().stateIn(viewModelScope).value

            _doctorName.value =
                useCases.readDoctorNameUseCase().stateIn(viewModelScope).value

            _doctorImage.value =
                useCases.readeDoctorImageUseCase().stateIn(viewModelScope).value
        }
    }

}