package com.example.dr_tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class DoctorApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val doctors: List<Doctor> = emptyList(),
    val lastUpdated: Long? = null
)