package com.example.dr_tryaq.domain.use_cases.save_onboarding

import com.example.dr_tryaq.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}