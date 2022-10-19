package com.example.dr_tryaq.domain.use_cases.get_all_services

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Service
import kotlinx.coroutines.flow.Flow

class GetAllServicesUseCase (
    private val repository: Repository
) {
    operator fun invoke(departmentId: Int): Flow<PagingData<Service>> {
        return repository.getDepartmentServices(departmentId = departmentId)
    }
}