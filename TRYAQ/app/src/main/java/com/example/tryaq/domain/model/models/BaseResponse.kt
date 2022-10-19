package com.example.tryaq.domain.model.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val success: Boolean?=false,
    val code: Int?=0,
    val message: String?="",
    val lastUpdated: Long? = null
)
