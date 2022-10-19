package com.example.tryaq.domain.use_cases.sign_up

import androidx.paging.PagingData
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class SignUpUseCase (
    private val repository: Repository
) {
    operator fun invoke(
        patientId: Int,
        password: String
    ): Flow<PagingData<BaseResponse>> {
        return repository.signUp(
            patientId=patientId,
            password = password
        )
    }
}