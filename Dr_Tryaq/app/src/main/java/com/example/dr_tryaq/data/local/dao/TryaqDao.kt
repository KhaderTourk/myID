package com.example.dr_tryaq.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dr_tryaq.domain.model.models.*

@Dao
interface TryaqDao {

    // Ads ----------
    @Query("SELECT * FROM dr_ad_table")
    fun getAllAds(): PagingSource<Int, Ad>

    @Query("SELECT * FROM dr_ad_table WHERE id=:id")
    fun getSelectedAd(id: Int): Ad

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAds(ads: List<Ad>)

    @Query("DELETE FROM dr_ad_table")
    suspend fun deleteAllAds()

    // Appointment -------------
    @Query("SELECT * FROM dr_appointments_table")
    fun getAllAppointments(): PagingSource<Int, Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAppointments(appointments: List<Appointment>)

    @Query("DELETE FROM dr_appointments_table")
    suspend fun deleteAllAppointments()

    @Query("DELETE FROM dr_appointments_table WHERE id=:appointmentId")
    suspend fun deleteAppointment(appointmentId: Int):Int

    // All Appointment -------------
    @Query("SELECT * FROM dr_doctor_table")
    fun getAllAppointments2(): PagingSource<Int, Doctor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAppointments2(appointments: List<Doctor>)

    @Query("DELETE FROM dr_doctor_table")
    suspend fun deleteAllAppointments2()

    // Departments -------------
    @Query("SELECT * FROM dr_department_table")
    fun getAllDepartments(): PagingSource<Int, Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDepartments(departments: List<Department>)

    @Query("DELETE FROM dr_department_table")
    suspend fun deleteAllDepartments()

    // Diagnosis -------------
    @Query("SELECT * FROM dr_diagnosis_table")
    fun getAllDiagnosis(): PagingSource<Int, Diagnosis>

    @Query("SELECT * FROM dr_diagnosis_table WHERE id=:id")
    fun getSelectedDiagnosis(id: Int): Diagnosis

    @Query("SELECT * FROM dr_diagnosis_table GROUP BY patientId")
    fun getMyPatients(): PagingSource<Int, Diagnosis>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiagnosis(diagnosis: List<Diagnosis>)

    @Query("DELETE FROM dr_diagnosis_table")
    suspend fun deleteAllDiagnosis()

    // Medicine -------------
    @Query("SELECT * FROM dr_medicine_table")
    fun getAllMedicines(): PagingSource<Int, Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicines(medicines: List<Medicine>)

    @Query("DELETE FROM dr_medicine_table")
    suspend fun deleteAllMedicines()

    // Patient -------------
    @Query("SELECT * FROM dr_patient_table")
    fun getAllPatients(): PagingSource<Int, Patient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPatients(patients: List<Patient>)

    @Query("DELETE FROM dr_patient_table")
    suspend fun deleteAllPatients()

    // Doctor -------------
    @Query("SELECT * FROM dr_doctor_table")
    fun getAllDoctors(): PagingSource<Int, Doctor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDoctors(doctors: List<Doctor>)

    @Query("DELETE FROM dr_doctor_table")
    suspend fun deleteAllDoctors()

    // Service -------------
    @Query("SELECT * FROM dr_service_table")
    fun getAllServices(): PagingSource<Int, Service>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addServices(services: List<Service>)

    @Query("DELETE FROM dr_service_table")
    suspend fun deleteAllServices()


}