package com.example.dr_tryaq.domain.use_cases.get_patient

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Doctor
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: Repository
) {
    operator fun invoke(doctorId: Int, password: String): Flow<PagingData<Doctor>> {
        return repository.login(doctorId = doctorId, password = password)
    }
}