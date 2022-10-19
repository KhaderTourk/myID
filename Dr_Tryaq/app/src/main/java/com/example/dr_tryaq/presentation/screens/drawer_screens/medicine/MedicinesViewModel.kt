package com.example.dr_tryaq.presentation.screens.drawer_screens.medicine

import androidx.lifecycle.ViewModel
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MedicinesViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getMedicines = useCases.getAllMedicinesUseCase()
}