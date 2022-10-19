package com.example.dr_tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class AppointmentApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val appointments: List<Appointment> = emptyList(),
    val lastUpdated: Long? = null
)