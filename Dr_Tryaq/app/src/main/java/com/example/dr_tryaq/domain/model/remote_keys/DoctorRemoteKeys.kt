package com.example.dr_tryaq.domain.model.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dr_tryaq.util.Constants.DOCTOR_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = DOCTOR_REMOTE_KEY_DATABASE_TABLE)
data class DoctorRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)