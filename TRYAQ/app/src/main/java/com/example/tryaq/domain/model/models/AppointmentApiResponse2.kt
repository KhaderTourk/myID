package com.example.tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class AppointmentApiResponse2 (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val appointments: List<Appointment2> = emptyList(),
    val lastUpdated: Long? = null
)