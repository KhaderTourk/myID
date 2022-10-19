package com.example.tryaq.domain.model.models

import com.google.gson.annotations.SerializedName

data class AppointmentActions (
    @SerializedName("cancel")
    val baseResponse: BaseResponse
)