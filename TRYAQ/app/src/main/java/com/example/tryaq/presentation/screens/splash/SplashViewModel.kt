package com.example.tryaq.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryaq.domain.use_cases.UseCases
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

    private val _patientId = MutableStateFlow(-1)
    val patientId: StateFlow<Int> = _patientId

    private val _patientName = MutableStateFlow("")
    val patientName: StateFlow<String> = _patientName

    private val _patientImage = MutableStateFlow("")
    val patientImage: StateFlow<String> = _patientImage

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value

            _rememberMeChecked.value =
            useCases.readRememberMeUseCase().stateIn(viewModelScope).value

            _patientId.value =
                useCases.readPatientIdUseCase().stateIn(viewModelScope).value

            _patientName.value =
                useCases.readPatientNameUseCase().stateIn(viewModelScope).value

            _patientImage.value =
                useCases.readePatientImageUseCase().stateIn(viewModelScope).value
        }
    }

}