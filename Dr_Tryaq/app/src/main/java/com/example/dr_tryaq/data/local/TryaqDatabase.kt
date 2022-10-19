package com.example.dr_tryaq.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dr_tryaq.data.local.dao.TryaqDao
import com.example.dr_tryaq.data.local.dao.TryaqRemoteKeysDao
import com.example.dr_tryaq.domain.model.models.*
import com.example.dr_tryaq.domain.model.remote_keys.*

@Database(
    entities = [
        Ad::class, AdRemoteKeys::class,
        Appointment::class, AppointmentRemoteKeys::class,
        Department::class, DepartmentRemoteKeys::class,
        Diagnosis::class, DiagnosisRemoteKeys::class,
        Doctor::class, DoctorRemoteKeys::class,
        Medicine::class, MedicineRemoteKeys::class,
        Patient::class, PatientRemoteKeys::class,
        Service::class, ServiceRemoteKeys::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class TryaqDatabase: RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): TryaqDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, TryaqDatabase::class.java)
            } else {
                Room.databaseBuilder(context, TryaqDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    abstract fun tryaqDao(): TryaqDao
    abstract fun tryaqRemoteKeysDao(): TryaqRemoteKeysDao
}
