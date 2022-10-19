package com.example.dr_tryaq.domain.use_cases.save_remember_me

import com.example.dr_tryaq.data.repository.Repository


class SaveRememberMeUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(isChecked: Boolean) {
        repository.saveRememberMeState(isChecked = isChecked)
    }
}