package com.example.tryaq.presentation.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.tryaq.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoadingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val appointmentId = savedStateHandle.get<Int>(Constants.CANCEL_APPOINTMENT_KEY)
}