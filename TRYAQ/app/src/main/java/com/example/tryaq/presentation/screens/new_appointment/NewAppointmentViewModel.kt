package com.example.tryaq.presentation.screens.new_appointment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.tryaq.domain.use_cases.UseCases
import com.example.tryaq.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewAppointmentViewModel @Inject constructor(
   useCases: UseCases,
   private val savedStateHandle: SavedStateHandle
): ViewModel() {
   val getAllAppointment = useCases.getAllAppointmentsUseCase2()

   fun getServiceId(): Int{
      return savedStateHandle.get<Int>(Constants.NEW_APPOINTMENT_KEY)?: 0
   }
}