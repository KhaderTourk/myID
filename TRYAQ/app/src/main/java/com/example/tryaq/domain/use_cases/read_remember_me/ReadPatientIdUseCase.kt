package com.example.tryaq.domain.use_cases.read_remember_me

import com.example.tryaq.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadPatientIdUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Int> {
        return repository.readPatientId()
    }
}