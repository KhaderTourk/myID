package com.example.tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class PatientApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val patient: List<Patient> = emptyList(),
    val lastUpdated: Long? = null
)