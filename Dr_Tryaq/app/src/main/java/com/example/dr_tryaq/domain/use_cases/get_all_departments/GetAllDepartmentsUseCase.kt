package com.example.dr_tryaq.domain.use_cases.get_all_departments

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Department
import kotlinx.coroutines.flow.Flow

class GetAllDepartmentsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Department>> {
        return repository.getAllDepartments()
    }
}