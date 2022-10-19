package com.example.tryaq.domain.use_cases.get_all_departments

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Ad
import com.example.tryaq.domain.model.models.Department
import kotlinx.coroutines.flow.Flow

class GetAllDepartmentsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Department>> {
        return repository.getAllDepartments()
    }
}