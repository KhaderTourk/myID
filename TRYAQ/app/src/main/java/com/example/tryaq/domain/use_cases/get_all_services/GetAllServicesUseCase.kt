package com.example.tryaq.domain.use_cases.get_all_services

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Service
import kotlinx.coroutines.flow.Flow

class GetAllServicesUseCase (
    private val repository: Repository
) {
    operator fun invoke(departmentId: Int): Flow<PagingData<Service>> {
        return repository.getDepartmentServices(departmentId = departmentId)
    }
}