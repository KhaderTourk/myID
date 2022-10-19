package com.example.dr_tryaq.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dr_tryaq.domain.model.remote_keys.*

@Dao
interface TryaqRemoteKeysDao {

    // Ads -----------
    @Query("SELECT * FROM dr_ad_remote_key_table WHERE id = :id")
    suspend fun getAdRemoteKeys(id: Int): AdRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAdRemoteKeys(adRemoteKeys: List<AdRemoteKeys>)

    @Query("DELETE FROM dr_ad_remote_key_table")
    suspend fun deleteAllAdRemoteKeys()

    // Appointment -----------
    @Query("SELECT * FROM dr_appointments_remote_key_table WHERE id = :id")
    suspend fun getAppointmentRemoteKeys(id: Int): AppointmentRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAppointmentRemoteKeys(appointmentRemoteKeys: List<AppointmentRemoteKeys>)

    @Query("DELETE FROM dr_appointments_remote_key_table")
    suspend fun deleteAllAppointmentRemoteKeys()

    // All Appointment -----------
    @Query("SELECT * FROM dr_doctor_remote_key_table WHERE id = :id")
    suspend fun getAppointmentRemoteKeys2(id: Int): DoctorRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAppointmentRemoteKeys2(appointmentRemoteKeys: List<DoctorRemoteKeys>)

    @Query("DELETE FROM dr_doctor_remote_key_table")
    suspend fun deleteAllAppointmentRemoteKeys2()

    // Departments -----------
    @Query("SELECT * FROM dr_department_remote_key_table WHERE id = :id")
    suspend fun getDepartmentRemoteKeys(id: Int): DepartmentRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllDepartmentRemoteKeys(departmentRemoteKeys: List<DepartmentRemoteKeys>)

    @Query("DELETE FROM dr_department_remote_key_table")
    suspend fun deleteAllDepartmentRemoteKeys()

    // Diagnosis -----------
    @Query("SELECT * FROM dr_diagnosis_remote_key_table WHERE id = :id")
    suspend fun getDiagnosisRemoteKeys(id: Int): DiagnosisRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllDiagnosisRemoteKeys(diagnosisRemoteKeys: List<DiagnosisRemoteKeys>)

    @Query("DELETE FROM dr_diagnosis_remote_key_table")
    suspend fun deleteAllDiagnosisRemoteKeys()

    // Medicine -----------
    @Query("SELECT * FROM dr_medicine_remote_key_table WHERE id = :id")
    suspend fun getMedicineRemoteKeys(id: Int): MedicineRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMedicineRemoteKeys(medicineRemoteKeys: List<MedicineRemoteKeys>)

    @Query("DELETE FROM dr_medicine_remote_key_table")
    suspend fun deleteAllMedicineRemoteKeys()

    // Patient -----------
    @Query("SELECT * FROM dr_patient_remote_key_table WHERE id = :id")
    suspend fun getPatientRemoteKeys(id: Int): PatientRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPatientRemoteKeys(patientRemoteKeys: List<PatientRemoteKeys>)

    @Query("DELETE FROM dr_patient_remote_key_table")
    suspend fun deleteAllPatientRemoteKeys()

    // Patient -----------
    @Query("SELECT * FROM dr_doctor_remote_key_table WHERE id = :id")
    suspend fun getDoctorRemoteKeys(id: Int): DoctorRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllDoctorRemoteKeys(doctorRemoteKeys: List<DoctorRemoteKeys>)

    @Query("DELETE FROM dr_doctor_remote_key_table")
    suspend fun deleteAllDoctorRemoteKeys()

    // Service -----------
    @Query("SELECT * FROM dr_service_remote_key_table WHERE id = :id")
    suspend fun getServiceRemoteKeys(id: Int): ServiceRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllServiceRemoteKeys(serviceRemoteKeys: List<ServiceRemoteKeys>)

    @Query("DELETE FROM dr_service_remote_key_table")
    suspend fun deleteAllServiceRemoteKeys()
}