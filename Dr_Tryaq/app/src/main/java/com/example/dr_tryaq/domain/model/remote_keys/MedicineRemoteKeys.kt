package com.example.dr_tryaq.domain.model.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dr_tryaq.util.Constants.MEDICINES_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = MEDICINES_REMOTE_KEY_DATABASE_TABLE)
data class MedicineRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)