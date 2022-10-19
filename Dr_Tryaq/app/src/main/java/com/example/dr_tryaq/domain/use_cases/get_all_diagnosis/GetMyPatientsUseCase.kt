package com.example.dr_tryaq.domain.use_cases.get_all_diagnosis

import androidx.paging.PagingSource
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Diagnosis

class GetMyPatientsUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(): PagingSource<Int, Diagnosis> {
        return repository.getMyPatients()
    }
}