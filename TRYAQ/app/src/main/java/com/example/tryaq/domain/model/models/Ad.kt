package com.example.tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryaq.util.Constants.ADS_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = ADS_DATABASE_TABLE)
data class Ad (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    var image: String,
    val date: String,
    val status: Int
)