package com.example.tryaq.domain.use_cases.save_remember_me

import com.example.tryaq.data.repository.Repository

class SaveRememberMeUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(isChecked: Boolean) {
        repository.saveRememberMeState(isChecked = isChecked)
    }
}