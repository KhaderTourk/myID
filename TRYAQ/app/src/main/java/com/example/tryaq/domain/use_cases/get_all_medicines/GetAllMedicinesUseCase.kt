package com.example.tryaq.domain.use_cases.get_all_medicines

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Medicine
import kotlinx.coroutines.flow.Flow

class GetAllMedicinesUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Medicine>> {
        return repository.getAllMedicines()
    }
}