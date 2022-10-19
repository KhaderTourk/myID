package com.example.dr_tryaq.presentation.screens.new_service

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.dr_tryaq.domain.model.models.BaseResponse
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewServiceViewModel  @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    fun changeServiceState(serviceId: Int): Flow<PagingData<BaseResponse>> {
        return useCases.changeServiceStateUseCase(serviceId = serviceId)
    }

    fun newService(departmentId: Int, name: String, price: String): Flow<PagingData<BaseResponse>> {
        return useCases.newServiceUseCase(departmentId = departmentId, name = name, price = price, image = "")
    }


}