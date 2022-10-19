package com.example.tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class MedicineApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val medicines: List<Medicine> = emptyList(),
    val lastUpdated: Long? = null
)