package com.example.dr_tryaq.domain.use_cases.save_remember_me

import com.example.dr_tryaq.data.repository.Repository

class SaveDoctorDepartmentUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(department: Int) {
        repository.saveDoctorDepartment(department = department)
    }
}