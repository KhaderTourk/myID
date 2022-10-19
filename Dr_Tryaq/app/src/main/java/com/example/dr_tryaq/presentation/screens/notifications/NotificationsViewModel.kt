package com.example.dr_tryaq.presentation.screens.notifications

import androidx.lifecycle.ViewModel
import com.example.dr_tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllAds = useCases.getAllAdsUseCase()
}