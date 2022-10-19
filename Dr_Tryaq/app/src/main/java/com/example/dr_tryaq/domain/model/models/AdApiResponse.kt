package com.example.dr_tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class AdApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val ads: List<Ad> = emptyList(),
    val lastUpdated: Long? = null
)
