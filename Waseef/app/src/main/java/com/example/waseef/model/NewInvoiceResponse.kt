package com.example.waseef.model

import com.google.gson.annotations.SerializedName

data class NewInvoiceResponse (
    @SerializedName("Response")
    val response: Invoice,
    @SerializedName("StatusMessage")
    val message: String,
    @SerializedName("Status")
    val code: Int
    )