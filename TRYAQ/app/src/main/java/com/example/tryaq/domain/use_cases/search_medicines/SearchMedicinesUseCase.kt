package com.example.tryaq.domain.use_cases.search_medicines

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.Medicine
import kotlinx.coroutines.flow.Flow

class SearchMedicinesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Medicine>> {
        return repository.searchMedicines(query = query)
    }
}