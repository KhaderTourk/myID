package com.example.dr_tryaq.domain.use_cases.read_remember_me

import com.example.dr_tryaq.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadRememberMeUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readRememberMeState()
    }
}