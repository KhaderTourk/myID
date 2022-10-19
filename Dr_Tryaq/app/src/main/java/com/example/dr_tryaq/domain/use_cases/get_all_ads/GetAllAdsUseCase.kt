package com.example.dr_tryaq.domain.use_cases.get_all_ads

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.Ad
import kotlinx.coroutines.flow.Flow

class GetAllAdsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Ad>> {
        return repository.getAllAds()
    }
}