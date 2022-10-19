package com.example.waseef.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("ProjectCode")
    val projectCode: String,
    @SerializedName("ContractNumber")
    val contractNumber: String,
    @SerializedName("InvoiceNumber")
    val invoiceNumber: Int,
    @SerializedName("chargetype")
    val chargeType: String,
    @SerializedName("TotalAmount")
    val totalAmount: Double,
    @SerializedName("PaidAmount")
    val paidAmount: Double,
    @SerializedName("PendingAmount")
    val pendingAmount: Double,
    @SerializedName("InvoiceDate")
    val invoiceDate: String,
    @SerializedName("InvoiceNotes")
    val invoiceNotes: String
)