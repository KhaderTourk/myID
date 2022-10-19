package com.example.tryaq.domain.use_cases.read_remember_me

import com.example.tryaq.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadePatientImageUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<String> {
        return repository.readPatientImage()
    }
}