package com.example.tryaq.presentation.screens.drawer_screens.medicine.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tryaq.domain.model.models.Medicine
import com.example.tryaq.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("د")
    val searchQuery = _searchQuery

    private val _searchedMedicines = MutableStateFlow<PagingData<Medicine>>(PagingData.empty())
    val searchedMedicines = _searchedMedicines

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchMedicines(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchMedicinesUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchedMedicines.value = it
            }
        }
    }

}