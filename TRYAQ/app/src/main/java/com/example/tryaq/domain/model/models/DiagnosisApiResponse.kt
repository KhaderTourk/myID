package com.example.tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val diagnosis: List<Diagnosis> = emptyList(),
    val lastUpdated: Long? = null
)