package com.example.tryaq.domain.use_cases.read_remember_me

import com.example.tryaq.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadRememberMeUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readRememberMeState()
    }
}