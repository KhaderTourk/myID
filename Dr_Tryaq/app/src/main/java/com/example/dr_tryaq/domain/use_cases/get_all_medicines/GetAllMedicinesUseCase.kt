package com.example.dr_tryaq.domain.use_cases.get_all_medicines

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Medicine
import kotlinx.coroutines.flow.Flow

class GetAllMedicinesUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Medicine>> {
        return repository.getAllMedicines()
    }
}