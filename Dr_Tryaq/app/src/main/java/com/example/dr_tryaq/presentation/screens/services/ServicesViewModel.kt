package com.example.dr_tryaq.presentation.screens.services

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.dr_tryaq.domain.model.models.Service
import com.example.dr_tryaq.domain.use_cases.UseCases
import com.example.dr_tryaq.util.Constants.DETAILS_DEPARTMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getDepartmentId(): Int{
        return savedStateHandle.get<Int>(DETAILS_DEPARTMENT_KEY)?: 0
    }

    fun getServices(departmentId: Int): Flow<PagingData<Service>> {
        return useCases.getAllServicesUseCase(departmentId = departmentId)
    }

}