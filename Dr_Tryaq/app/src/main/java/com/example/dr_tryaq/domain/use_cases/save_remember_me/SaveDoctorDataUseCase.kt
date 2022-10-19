package com.example.dr_tryaq.domain.use_cases.save_remember_me

import com.example.dr_tryaq.data.repository.Repository


class SaveDoctorDataUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int,name: String, image: String) {
        repository.saveDoctorData(id = id, name = name, image = image)
    }
}