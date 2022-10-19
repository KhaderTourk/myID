package com.example.tryaq.presentation.screens.bottom_nav_screens.departments

import androidx.lifecycle.ViewModel
import com.example.tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
   useCases: UseCases
): ViewModel() {
    val getAllDepartments = useCases.getAllDepartmentsUseCase()
}