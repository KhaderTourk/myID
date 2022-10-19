package com.example.waseef.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Response")
    val response: User,
    @SerializedName("StatusMessage")
    val message: String,
    @SerializedName("Status")
    val code: Int
    )