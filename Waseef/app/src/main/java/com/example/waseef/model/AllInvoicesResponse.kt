package com.example.waseef.model

import com.google.gson.annotations.SerializedName

data class AllInvoicesResponse (
    @SerializedName("Response")
    val response: List<InvoiceListItem>,
    @SerializedName("StatusMessage")
    val message: String,
    @SerializedName("Status")
    val code: Int
)