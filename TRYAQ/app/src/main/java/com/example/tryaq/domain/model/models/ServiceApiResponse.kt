package com.example.tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class ServiceApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val services: List<Service> = emptyList(),
    val lastUpdated: Long? = null
)