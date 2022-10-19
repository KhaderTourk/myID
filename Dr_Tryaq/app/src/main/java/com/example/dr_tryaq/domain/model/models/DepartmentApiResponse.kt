package com.example.dr_tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class DepartmentApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val departments: List<Department> = emptyList(),
    val lastUpdated: Long? = null
)
