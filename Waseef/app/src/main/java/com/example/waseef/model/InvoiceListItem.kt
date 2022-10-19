package com.example.waseef.model

data class InvoiceListItem (
    val TransactionStatus: Int = 0,
    val FullName: String? = null,
    val Description: String? = null,
    val TransactionDate: String? = null,
    val Amount: Double = 0.0
        )
