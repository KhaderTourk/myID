package com.example.tryaq.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tryaq.domain.model.models.*

@Dao
interface TryaqDao {

    // Ads ----------
    @Query("SELECT * FROM ad_table")
    fun getAllAds(): PagingSource<Int, Ad>

    @Query("SELECT * FROM ad_table WHERE id=:id")
    fun getSelectedAd(id: Int): Ad

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAds(ads: List<Ad>)

    @Query("DELETE FROM ad_table")
    suspend fun deleteAllAds()

    // Appointment -------------
    @Query("SELECT * FROM appointments_table")
    fun getAllAppointments(): PagingSource<Int, Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAppointments(appointments: List<Appointment>)

    @Query("DELETE FROM appointments_table")
    suspend fun deleteAllAppointments()

    @Query("DELETE FROM appointments_table WHERE id=:appointmentId")
    suspend fun deleteAppointment(appointmentId: Int):Int

    // All Appointment -------------
    @Query("SELECT * FROM appointments2_table")
    fun getAllAppointments2(): PagingSource<Int, Appointment2>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAppointments2(appointments: List<Appointment2>)

    @Query("DELETE FROM appointments2_table")
    suspend fun deleteAllAppointments2()

    // Departments -------------
    @Query("SELECT * FROM department_table")
    fun getAllDepartments(): PagingSource<Int, Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDepartments(departments: List<Department>)

    @Query("DELETE FROM department_table")
    suspend fun deleteAllDepartments()

    // Diagnosis -------------
    @Query("SELECT * FROM diagnosis_table")
    fun getAllDiagnosis(): PagingSource<Int, Diagnosis>

    @Query("SELECT * FROM diagnosis_table WHERE id=:id")
    fun getSelectedDiagnosis(id: Int): Diagnosis

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiagnosis(diagnosis: List<Diagnosis>)

    @Query("DELETE FROM diagnosis_table")
    suspend fun deleteAllDiagnosis()

    // Medicine -------------
    @Query("SELECT * FROM medicine_table")
    fun getAllMedicines(): PagingSource<Int, Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicines(medicines: List<Medicine>)

    @Query("DELETE FROM medicine_table")
    suspend fun deleteAllMedicines()

    // Patient -------------
    @Query("SELECT * FROM patient_table")
    fun getAllPatients(): PagingSource<Int, Patient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPatients(patients: List<Patient>)

    @Query("DELETE FROM patient_table")
    suspend fun deleteAllPatients()

    // Service -------------
    @Query("SELECT * FROM service_table")
    fun getAllServices(): PagingSource<Int, Service>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addServices(services: List<Service>)

    @Query("DELETE FROM service_table")
    suspend fun deleteAllServices()


}