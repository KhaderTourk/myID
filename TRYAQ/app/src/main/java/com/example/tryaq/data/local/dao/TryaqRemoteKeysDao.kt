package com.example.tryaq.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tryaq.domain.model.remote_keys.*

@Dao
interface TryaqRemoteKeysDao {

    // Ads -----------
    @Query("SELECT * FROM ad_remote_key_table WHERE id = :id")
    suspend fun getAdRemoteKeys(id: Int): AdRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAdRemoteKeys(adRemoteKeys: List<AdRemoteKeys>)

    @Query("DELETE FROM ad_remote_key_table")
    suspend fun deleteAllAdRemoteKeys()

    // Appointment -----------
    @Query("SELECT * FROM appointments_remote_key_table WHERE id = :id")
    suspend fun getAppointmentRemoteKeys(id: Int): AppointmentRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAppointmentRemoteKeys(appointmentRemoteKeys: List<AppointmentRemoteKeys>)

    @Query("DELETE FROM appointments_remote_key_table")
    suspend fun deleteAllAppointmentRemoteKeys()

    // All Appointment -----------
    @Query("SELECT * FROM appointments2_remote_key_table WHERE id = :id")
    suspend fun getAppointmentRemoteKeys2(id: Int): Appointment2RemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAppointmentRemoteKeys2(appointmentRemoteKeys: List<Appointment2RemoteKeys>)

    @Query("DELETE FROM appointments2_remote_key_table")
    suspend fun deleteAllAppointmentRemoteKeys2()

    // Departments -----------
    @Query("SELECT * FROM department_remote_key_table WHERE id = :id")
    suspend fun getDepartmentRemoteKeys(id: Int): DepartmentRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllDepartmentRemoteKeys(departmentRemoteKeys: List<DepartmentRemoteKeys>)

    @Query("DELETE FROM department_remote_key_table")
    suspend fun deleteAllDepartmentRemoteKeys()

    // Diagnosis -----------
    @Query("SELECT * FROM diagnosis_remote_key_table WHERE id = :id")
    suspend fun getDiagnosisRemoteKeys(id: Int): DiagnosisRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllDiagnosisRemoteKeys(diagnosisRemoteKeys: List<DiagnosisRemoteKeys>)

    @Query("DELETE FROM diagnosis_remote_key_table")
    suspend fun deleteAllDiagnosisRemoteKeys()

    // Medicine -----------
    @Query("SELECT * FROM medicine_remote_key_table WHERE id = :id")
    suspend fun getMedicineRemoteKeys(id: Int): MedicineRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMedicineRemoteKeys(medicineRemoteKeys: List<MedicineRemoteKeys>)

    @Query("DELETE FROM medicine_remote_key_table")
    suspend fun deleteAllMedicineRemoteKeys()

    // Patient -----------
    @Query("SELECT * FROM patient_remote_key_table WHERE id = :id")
    suspend fun getPatientRemoteKeys(id: Int): PatientRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPatientRemoteKeys(patientRemoteKeys: List<PatientRemoteKeys>)

    @Query("DELETE FROM patient_remote_key_table")
    suspend fun deleteAllPatientRemoteKeys()

    // Service -----------
    @Query("SELECT * FROM service_remote_key_table WHERE id = :id")
    suspend fun getServiceRemoteKeys(id: Int): ServiceRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllServiceRemoteKeys(serviceRemoteKeys: List<ServiceRemoteKeys>)

    @Query("DELETE FROM service_remote_key_table")
    suspend fun deleteAllServiceRemoteKeys()
}