package com.example.dr_tryaq.domain.model.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dr_tryaq.util.Constants.PATIENT_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = PATIENT_REMOTE_KEY_DATABASE_TABLE)
data class PatientRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)