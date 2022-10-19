package com.example.caching.features.Parts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.caching.data.PartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
repository: PartRepository
) : ViewModel() {
    val parts = repository.getParts().asLiveData()

}

