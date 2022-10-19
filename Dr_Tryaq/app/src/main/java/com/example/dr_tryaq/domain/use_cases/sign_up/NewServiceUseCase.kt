package com.example.dr_tryaq.domain.use_cases.sign_up

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class NewServiceUseCase (
    private val repository: Repository
) {
    operator fun invoke(
        departmentId: Int,
        name: String,
        image: String,
        price: String,
    ): Flow<PagingData<BaseResponse>> {
        return repository.newService(
            departmentId = departmentId,
            name = name,
            image = image,
            price = price
        )
    }
}