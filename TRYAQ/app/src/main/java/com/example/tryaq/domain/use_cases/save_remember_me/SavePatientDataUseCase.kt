package com.example.tryaq.domain.use_cases.save_remember_me

import com.example.tryaq.data.repository.Repository

class SavePatientDataUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int,name: String, image: String) {
        repository.savePatientData(id = id, name = name, image = image)
    }
}