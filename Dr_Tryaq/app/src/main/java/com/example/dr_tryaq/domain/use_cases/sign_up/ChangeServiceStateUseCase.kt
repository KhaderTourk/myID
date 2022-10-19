package com.example.dr_tryaq.domain.use_cases.sign_up

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class ChangeServiceStateUseCase(
    private val repository: Repository
) {
    operator fun invoke(
        serviceId: Int
    ): Flow<PagingData<BaseResponse>> {
        return repository.changeServiceState(
            serviceId = serviceId
        )
    }
}